package intership.dev.contact;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import intership.dev.contact.fragment.Fragment_list_contact;

/**
 * Created by tran on 7/22/15.
 */
public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment_list_contact fragment = new Fragment_list_contact();

        transaction.add(R.id.lnContain, fragment);
        transaction.commit();
    }
}