package intership.dev.contact.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import intership.dev.contact.EditContactActivity;
import intership.dev.contact.R;
import intership.dev.contact.model.Contact;
import intership.dev.contact.widget.CircleImageView;

/**
 * Created by tran on 7/21/15.
 */
public class ContactAdapter extends ArrayAdapter<Contact> {

    private List<Contact> mContact;
    private Activity mActivity;

    public ContactAdapter(Activity activity, int resourcdId, List<Contact> contacts) {
        super(activity, resourcdId, contacts);
        this.mContact = contacts;
        this.mActivity = activity;
    }

    public class ViewHodel {
        ImageView  imgEdit, imgDelete;
        TextView tvUsername;
        CircleImageView imgAvatar;
    }

    public View getView(final int position, View convertview, final ViewGroup parent) {

        ViewHodel hodel = null;

        final Contact contact = getItem(position);
        final LayoutInflater layoutInflater = (LayoutInflater) mActivity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertview == null) {

            convertview = layoutInflater.inflate(R.layout.item_list_contact, null);
            hodel = new ViewHodel();

            hodel.imgAvatar = (CircleImageView) convertview.findViewById(R.id.imgAvatar);
            hodel.tvUsername = (TextView) convertview.findViewById(R.id.txtUsername);
            hodel.imgEdit = (ImageView) convertview.findViewById(R.id.imgEdit);
            hodel.imgDelete = (ImageView) convertview.findViewById(R.id.imgDelete);

            convertview.setTag(hodel);

            hodel.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        final Dialog dialog = new Dialog(mActivity, R.style.Theme_Dialog);

                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_contact);

                        TextView txtName = (TextView) dialog.findViewById(R.id.txtdc1);
                        TextView txtOk = (TextView) dialog.findViewById(R.id.txtOk);
                        TextView txtCancel = (TextView) dialog.findViewById(R.id.txtCancel);

                        txtName.setText(Html.fromHtml("Are you sure you want to delete " +
                                "<b>" + contact.getmUsernameContact().toString() + "</b>" + "?"));

                        txtCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                        txtOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                remove(contact);
                                dialog.hide();
                            }
                        });
                        dialog.show();
                    }
            });

            hodel.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        Intent intent = new Intent(mActivity, EditContactActivity.class);
                        intent.putExtra("contact", contact);
                        intent.putExtra("position", position);
                        mActivity.startActivityForResult(intent, 1);
                    }
            });

        }
        else
            hodel = (ViewHodel) convertview.getTag();
            hodel.tvUsername.setText(contact.getmUsernameContact());
            hodel.imgAvatar.setImageResource(contact.getmAvatar());
            String mDescription = contact.getmDescription();

        return convertview;
    }
}
