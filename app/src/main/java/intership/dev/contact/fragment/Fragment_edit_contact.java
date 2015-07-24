package intership.dev.contact.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import intership.dev.contact.R;
import intership.dev.contact.model.Contact;
import intership.dev.contact.widget.CircleImageView;

/**
 * Created by tran on 7/22/15.
 */
public class Fragment_edit_contact extends Fragment implements View.OnClickListener {

	private Contact mModel;

	private CircleImageView imgAvatar;
	private TextView tvName, tvSave, tvCancel;
	private EditText edtDesc, edtUsername;
	private OnChangeItemListener mListenerOnChange;

	/**
	 *
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view =  inflater.inflate(R.layout.frament_edit_contact, container, false);
		init(view);
		return view;
	}

	public void init(View view) {

		 imgAvatar=(CircleImageView) view.findViewById(R.id.imgEditAvatar);
		 tvName=(TextView) view.findViewById(R.id.txtEditName);

		 edtUsername=(EditText) view.findViewById(R.id.edtEditname);
		 edtDesc=(EditText) view.findViewById(R.id.edtEditNoidung);

		 tvSave=(TextView) view.findViewById(R.id.txtEditSave);
		 tvCancel=(TextView) view.findViewById(R.id.txtEditCancel);

		 Bundle dataBundle = this.getArguments();
		 mModel = (Contact) dataBundle.getSerializable("Bundel");

		 edtUsername.setText(mModel.getmUsernameContact());
		 tvName.setText(mModel.getmUsernameContact());

		 imgAvatar.setImageResource(mModel.getmAvatar());
		 edtDesc.setText(mModel.getmDescription());

		 tvSave.setOnClickListener(this);
		 tvCancel.setOnClickListener(this);
	}

	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.txtEditSave:
				mModel.setmUsernameContact(edtUsername.getText().toString());
				mModel.setmDescription(edtDesc.getText().toString());
				mListenerOnChange.onChange(mModel);
				getActivity().onBackPressed();
				break;
			case R.id.txtCancel:
				getActivity().onBackPressed();
			case R.id.imgBack:
				getActivity().onBackPressed();
		}
	}

	public void setOnChangeItemListener(OnChangeItemListener listener) {
		mListenerOnChange = listener;
	}

	public interface OnChangeItemListener {
		void onChange(Contact contactModelmodel);
	}


}
