package intership.dev.contact.adapter;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import intership.dev.contact.R;
import intership.dev.contact.fragment.Fragment_edit_contact;
import intership.dev.contact.model.Contact;
import intership.dev.contact.widget.CircleImageView;

/**
 * Created by tran on 7/21/15.
 */
public class ContactAdapter extends BaseAdapter implements Fragment_edit_contact.OnChangeItemListener {

    private ArrayList<Contact> mContact = new ArrayList<>();
    private FragmentActivity mActivity;

    private FragmentManager mManager;
    private FragmentTransaction mTransaction;
    private Fragment_edit_contact mEditContactFragment;

    /**
     *  @param activity
     * @param contacts
     */
    public ContactAdapter(FragmentActivity activity,ArrayList<Contact> contacts) {
        this.mContact = contacts;
        this.mActivity = activity;
    }

    @Override
    public void onChange(Contact contactModelmodel) {
        notifyDataSetChanged();
    }

    /**
     * Create class ViewHodel to convert view
     */
    @Override
    public int getCount() {
        return mContact.size();
    }

    @Override
    public Object getItem(int positon) {
        return mContact.get(positon);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHoldel {
        ImageView  imgEdit, imgDelete;
        TextView tvUsername;
        CircleImageView imgAvatar;
    }

    /**
     *
     * @param position of List<Contact> mContact
     * @param convertview item in ListView
     * @param parent view parent of convertview
     * @return convertview
     */
    public View getView(final int position, View convertview, final ViewGroup parent) {

        ViewHoldel holdel = null;

        if(convertview == null) {

            convertview = LayoutInflater.from(mActivity).inflate(R.layout.item_list_contact, parent, false);
            holdel = new ViewHoldel();
            holdel.imgAvatar = (CircleImageView) convertview.findViewById(R.id.imgAvatar);
            holdel.tvUsername = (TextView) convertview.findViewById(R.id.txtUsername);
            holdel.imgEdit = (ImageView) convertview.findViewById(R.id.imgEdit);
            holdel.imgDelete = (ImageView) convertview.findViewById(R.id.imgDelete);
            convertview.setTag(holdel);
        }
        else {
            holdel = (ViewHoldel) convertview.getTag();
        }

        setValue(holdel, position);
        setEvent(holdel, mContact.get(position),mActivity);

        return convertview;
    }

    /**
     * method intent to EditContactFragment
     * @param contactModel is a object to refactor
     */
    private void call(Contact contactModel) {

        mManager = mActivity.getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();

        if (mEditContactFragment == null) {
            mEditContactFragment = new Fragment_edit_contact();
            mEditContactFragment.setOnChangeItemListener(this);
        }

        Bundle dataBundle = new Bundle();
        dataBundle.putSerializable("Bundel", contactModel);

        mEditContactFragment.setArguments(dataBundle);
        mTransaction.replace(R.id.lnContain, mEditContactFragment);
        mTransaction.addToBackStack(null);
        mTransaction.commit();
    }

    private void setValue(ViewHoldel holder, int position) {

        Contact model = (Contact) getItem(position);

        holder.tvUsername.setText(model.getmUsernameContact());
        holder.imgAvatar.setImageResource(model.getmAvatar());
    }

    private void setEvent(final ViewHoldel holder, final Contact contact, final FragmentActivity activity) {

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(activity, R.style.Theme_Dialog);
                dialog.setContentView(R.layout.dialog_contact);
                dialog.show();

                TextView tvTitle = (TextView) dialog.findViewById(R.id.txtdc1);
                tvTitle.setText(Html.fromHtml("Are you sure you want to delete " + contact.getmUsernameContact() + "?"));

                TextView tvOk = (TextView) dialog.findViewById(R.id.txtOk);
                tvOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mContact.remove(contact);
                        notifyDataSetChanged();
                        dialog.hide();
                    }
                });

            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                call(contact);
            }
        });
 }
}
