package intership.dev.contact.fragment;

import android.app.Activity;
import android.content.Intent;
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
public class Fragment_edit_contact extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        	      View view =  inflater.inflate(R.layout.frament_edit_contact, container, false);

					final Intent intent=getActivity().getIntent();

					final Contact contact=(Contact) intent.getSerializableExtra("contact");
					final int position=intent.getIntExtra("position", -1);

					final CircleImageView imgAvatar=(CircleImageView) view.findViewById(R.id.imgEditAvatar);
					final TextView tvName=(TextView) view.findViewById(R.id.txtEditName);

					final EditText edtUsername=(EditText) view.findViewById(R.id.edtEditname);
					final EditText edtDesc=(EditText) view.findViewById(R.id.edtEditNoidung);

					final TextView txtSave=(TextView) view.findViewById(R.id.txtEditSave);
					final TextView txtCancel=(TextView) view.findViewById(R.id.txtEditCancel);

					imgAvatar.setImageResource(contact.getmAvatar());
					tvName.setText(contact.getmUsernameContact());
					edtUsername.setText(contact.getmUsernameContact());
					edtDesc.setText(contact.getmDescription());

					txtCancel.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							getActivity().finish();
						}
					});

					txtSave.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							contact.setmUsernameContact(edtUsername.getText().toString());
							contact.setmDescription(edtDesc.getText().toString());
							intent.putExtra("position",position);
							intent.putExtra("contact", contact);
							getActivity().setResult(Activity.RESULT_OK,intent);
							getActivity().finish();
						}
					});
		return view;
	}

}
