package intership.dev.contact.model;

import java.io.Serializable;

/**
 * Created by tran on 7/21/15.
 */
public class Contact implements Serializable {

    public Contact() {
    }

    private int mAvatar;
    private String mUsernameContact;
    private String mDescription;

    public int getmAvatar() {
        return mAvatar;
    }

    public void setmAvatar(int mAvatar) {
        this.mAvatar = mAvatar;
    }

    public String getmUsernameContact() {
        return mUsernameContact;
    }

    public void setmUsernameContact(String mUsernameContact) {
        this.mUsernameContact = mUsernameContact;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
