package intership.dev.contact;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import intership.dev.contact.model.Contact;

/**
 * Created by tran on 7/24/15.
 */
public class Dialog_contact extends Dialog implements View.OnClickListener {

    private TextView mMessage;
    private TextView mTxtOk;
    private TextView mTxtCancel;
    private int mPosition;
    private OnClickContactDialog mListener;

    public Dialog_contact(Context context) {
        super(context);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_contact);
        init();

    }

    private void init(){
        mMessage = (TextView) findViewById(R.id.txtdc1);
        mTxtOk = (TextView) findViewById(R.id.txtOk);
        mTxtCancel = (TextView) findViewById(R.id.txtCancel);
        mTxtOk.setOnClickListener(this);
        mTxtCancel.setOnClickListener(this);

    }
    public void setDialogMessage(Contact contactModel) {
        mMessage.setText(Html.fromHtml("Are you sure you want to delete " + "<b>" +
                contactModel.getmUsernameContact() + "</b>" + "?"));
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int mPosition) {
        this.mPosition = mPosition;
    }


    public void setOnClickListViewContactListener(OnClickContactDialog listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View view) {

        if (mListener == null) {
            return;
        }
        int id = view.getId();
        if (id == mTxtOk.getId()) {
            mListener.onClickBtnOK(view);
        }
        if (id == mTxtCancel.getId()) {
            mListener.onClickBtnCancel(view);
        }
    }

    public interface OnClickContactDialog {
        /**
         * Called when a button OK has been clicked.
         *
         * @param v The view that was clicked.
         */
        void onClickBtnOK(View v);

        /**
         * Called when the button Cancel has been clicked.
         *
         * @param v The view that was clicked.
         */
        void onClickBtnCancel(View v);
    }
}
