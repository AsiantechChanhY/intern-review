package intership.dev.contact.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import intership.dev.contact.R;
import intership.dev.contact.adapter.ContactAdapter;
import intership.dev.contact.model.Contact;
import intership.dev.contact.widget.LoadMoreListView;

public class Fragment_list_contact extends Fragment {

    /**
     * Create data for ListView
     */
    public static final String[] NAME = new String[]{"Strawberry", "Banana", "Orange", "Mixed", "Abbott", "Abraham",
                                                      "Alvin", "Dalton", "Gale", "Halsey", "Isaac","Philbert"};

    public static final String[] DESC = new String[]{"1","2","3","4","5","6","7","8","9","0", "1"," 2"};

    public static final Integer[] AVATAR = new Integer[]{R.drawable.ic_avt1, R.drawable.ic_avt2, R.drawable.ic_avt3,
                                                        R.drawable.ic_avt4, R.drawable.ic_avt1, R.drawable.ic_avt1,
                                                        R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4,
                                                        R.drawable.ic_avt1, R.drawable.ic_avt1, R.drawable.ic_avt2};

    private LoadMoreListView mLvContact;
    private ContactAdapter mContactAdapter;
    private ArrayList<Contact> mContact;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setValue();
    }

    @Override
    public void onResume() {
        mContactAdapter = new ContactAdapter(getActivity(), mContact);
        mLvContact.setAdapter(mContactAdapter);

        mLvContact.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new LoadDataTask().execute();
            }
        });
        super.onResume();
    }

    /**
     * Called when the activity is first created.
     * @param savedInstanceState
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mListContact = inflater.inflate(R.layout.activity_main, container, false);

        mLvContact = (LoadMoreListView) mListContact.findViewById(R.id.lvContact);
        return mListContact;
    }

    private void setValue() {

        mContact = new ArrayList<>();

        for(int i=0; i < NAME.length; i++) {

            Contact item = new Contact();
            item.setmAvatar(AVATAR[i]);
            item.setmUsernameContact(NAME[i]);
            item.setmDescription(DESC[i]);
            mContact.add(item);
        }
    }

    /**
     * class load data new when listview finish
     */
    private class LoadDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            if (isCancelled()) {
                return null;
            }

            /**
             * Simulates a background task
              */
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            /**
             * add Loadmore Item
             */
            for (int i = 0; i < NAME.length; i++) {
                Contact item = new Contact();
                item.setmAvatar(AVATAR[i]);
                item.setmUsernameContact(NAME[i]);
                mContact.add(item);
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {

            /**
             *  We need notify the adapter that the data have been changed
             */
            mContactAdapter.notifyDataSetChanged();

            /**
             * Call onLoadMoreComplete when the LoadMore task, has finished
             */
            ((LoadMoreListView) mLvContact).onLoadMoreComplete();
            super.onPostExecute(result);
        }
        @Override
        protected void onCancelled() {

            /**
             * Notify the loading more operation has finished
             */
            ((LoadMoreListView) mLvContact).onLoadMoreComplete();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /**
         * Handle action bar item clicks here. The action bar will
         * automatically handle clicks on the Home/Up button, so long
         * as you specify a parent activity in AndroidManifest.xml.
         */
        int id = item.getItemId();

        /**
         * noinspection SimplifiableIfStatement
         */
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
