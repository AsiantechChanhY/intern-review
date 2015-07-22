package intership.dev.contact.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import intership.dev.contact.R;
import intership.dev.contact.model.Contact;

/**
 * Created by tran on 7/21/15.
 */
public class ContactAdapter extends ArrayAdapter<Contact> {

    Context mContext;

    public ContactAdapter(Context context, int resourcdId, List<Contact> items) {
        super(context, resourcdId, items);
        this.mContext = context;
    }

    public class ViewHodel {
        ImageView imgAvatar, imgEdit, imgDelete;
        TextView tvUsername;
    }

    public View getView(final int position, View convertview, final ViewGroup parent) {

        ViewHodel hodel = null;
        final Contact contact = getItem(position);

        final LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertview == null) {
            convertview = layoutInflater.inflate(R.layout.item_list_contact, null);

            hodel = new ViewHodel();
            hodel.imgAvatar = (ImageView) convertview.findViewById(R.id.imgAvatar);
            hodel.tvUsername = (TextView) convertview.findViewById(R.id.txtUsername);
            hodel.imgEdit = (ImageView) convertview.findViewById(R.id.imgEdit);
            hodel.imgDelete = (ImageView) convertview.findViewById(R.id.imgDelete);
            convertview.setTag(hodel);

            hodel.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final ImageView img = (ImageView) view.findViewById(R.id.imgDelete);

                    view.setSelected(!view.isSelected());

                    if (view.isSelected()) {

                        img.setBackgroundResource(R.drawable.ic_delete_on);
                        final Dialog dialog = new Dialog(mContext, R.style.Theme_Dialog);
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
                                img.setBackgroundResource(R.drawable.ic_delete);
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

                }
            });

            hodel.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

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
