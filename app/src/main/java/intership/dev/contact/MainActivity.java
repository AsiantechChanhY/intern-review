package intership.dev.contact;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import intership.dev.contact.adapter.ContactAdapter;
import intership.dev.contact.model.Contact;


public class MainActivity extends Activity {

    public static final String[] NAME = new String[]{"Strawberry", "Banana", "Orange", "Mixed", "Abbott",
            "Abraham", "Alvin", "Dalton", "Gale", "Halsey", "Isaac","Philbert"};

    public static final String[] DESC = new String[]{"1,2,3,4,5,6,7,8,9,0, 1, 2"};

    public static final Integer[] AVATAR = new Integer[]{R.drawable.ic_avt1, R.drawable.ic_avt2, R.drawable.ic_avt3,
            R.drawable.ic_avt4, R.drawable.ic_avt1, R.drawable.ic_avt1, R.drawable.ic_avt2, R.drawable.ic_avt3,
            R.drawable.ic_avt4, R.drawable.ic_avt1, R.drawable.ic_avt1, R.drawable.ic_avt2};

    ListView lvContact;
    List<Contact> mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContact = new ArrayList<Contact>();

        for(int i=0; i < NAME.length; i++) {
            Contact item = new Contact();
            item.setmAvatar(AVATAR[i]);
            item.setmUsernameContact(NAME[i]);
            mContact.add(item);
        }

        lvContact = (ListView) findViewById(R.id.lvContact);
        ContactAdapter contactAdapter = new ContactAdapter(this, R.layout.item_list_contact, mContact);
        lvContact.setAdapter(contactAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
