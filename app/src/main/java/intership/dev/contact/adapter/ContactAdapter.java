package intership.dev.contact.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import intership.dev.contact.Dialog_contact;
import intership.dev.contact.R;
import intership.dev.contact.fragment.Fragment_edit_contact;
import intership.dev.contact.model.Contact;
import intership.dev.contact.widget.CircleImageView;

/**
 * Created by tran on 7/21/15.
 */
public class ContactAdapter extends BaseAdapter implements Fragment_edit_contact.OnChangeItemListener,
        DialogInterface.OnDismissListener, Dialog_contact.OnClickContactDialog {

    private ArrayList<Contact> mContact = new ArrayList<>();
    private FragmentActivity mActivity;
    private Dialog_contact mDialog;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment_edit_contact mEditContactFragment;

    /**
     *  @param activity
     * @param contacts
     */

    public ContactAdapter(FragmentActivity activity,ArrayList<Contact> contacts) {
        this.mContact = contacts;
        this.mActivity = activity;
        mDialog = new Dialog_contact(mActivity);
        mDialog.setOnClickListViewContactListener(this);
        mDialog.setOnDismissListener(this);
    }

    @Override
    public void onChange(Contact contactModelmodel) {
        notifyDataSetChanged();
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

    }

    @Override
    public void onClickBtnOK(View v) {
        mContact.remove(mDialog.getPosition());
        notifyDataSetChanged();
        mDialog.dismiss();
    }

    @Override
    public void onClickBtnCancel(View v) {
        mDialog.dismiss();
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

    public class ViewHodel {
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

        ViewHodel hodel = null;

        if(convertview == null) {

            convertview = LayoutInflater.from(mActivity).inflate(R.layout.item_list_contact, parent, false);
            hodel = new ViewHodel();

            hodel.imgAvatar = (CircleImageView) convertview.findViewById(R.id.imgAvatar);
            hodel.tvUsername = (TextView) convertview.findViewById(R.id.txtUsername);
            hodel.imgEdit = (ImageView) convertview.findViewById(R.id.imgEdit);
            hodel.imgDelete = (ImageView) convertview.findViewById(R.id.imgDelete);

            convertview.setTag(hodel);
        }
        else {
            hodel = (ViewHodel) convertview.getTag();
        }

        setValue(hodel, position);
        setEvent(hodel, position);

        return convertview;
    }

    /**
     * method intent to EditContactFragment
     * @param contactModel is a object to refactor
     */
    private void callEditContactFragment(Contact contactModel) {
        mFragmentManager = mActivity.getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        if (mEditContactFragment == null) {
            mEditContactFragment = new Fragment_edit_contact();
            mEditContactFragment.setOnChangeItemListener(this);
        }
        Bundle dataBundle = new Bundle();
        dataBundle.putSerializable("dataBundle", contactModel);

        mEditContactFragment.setArguments(dataBundle);
        mFragmentTransaction.replace(R.id.lnContain, mEditContactFragment);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }



    private void setValue(ViewHodel holder, int position) {
        Contact model = (Contact) getItem(position);
        holder.tvUsername.setText(model.getmUsernameContact());
        holder.imgAvatar.setImageResource(model.getmAvatar());
    }

    private void setEvent(final ViewHodel holder, final int position) {
        final Contact model = (Contact) getItem(position);
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.setPosition(position);
                mDialog.show();
                mDialog.setDialogMessage(model);
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEditContactFragment(model);
            }
        });
 }
}
