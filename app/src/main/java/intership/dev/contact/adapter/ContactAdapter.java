package intership.dev.contact.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
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

    Context context;
    AlertDialog mDialog;

    public ContactAdapter(Context context, int resourcdId, List<Contact> items) {
        super(context, resourcdId, items);
        this.context = context;
    }

    public class ViewHodel {
        ImageView imgAvatar, imgEdit, imgDelete;
        TextView tvUsername;
    }

    public View getView(final int position, View convertview, final ViewGroup parent) {

        ViewHodel hodel = null;
        final Contact contact = getItem(position);

        final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

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

                    final Dialog dialog = new Dialog(context,R.style.Theme_Dialog);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                    dialog.setContentView(R.layout.dialog_contact);

                    TextView txtName = (TextView) dialog.findViewById(R.id.txtdc1);

                    txtName.setText(Html.fromHtml("Are you sure you want to delete " +
                            "<b>" + contact.getmUsernameContact().toString() + "</b>" + "?"));


                    Drawable d = new ColorDrawable(Color.WHITE);
                    d.setAlpha(180);
                    dialog.getWindow().setBackgroundDrawable(d);
                    dialog.show();
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
