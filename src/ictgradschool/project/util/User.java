package ictgradschool.project.util;


public class User {
    private int id;
    private String fName;
    private String lName;
    private String userName;
    private String hashCode;
    private String salt;
    private String dateOfBirth;
    private String profileImage;
    private String bio;

    public User(int id, String fName, String lName, String userName, String hashCode, String salt, String dateOfBirth, String profileImage, String bio) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.userName = userName;
        this.hashCode = hashCode;
        this.salt = salt;
        this.dateOfBirth = dateOfBirth;
        this.profileImage = profileImage;
        this.bio = bio;
    }

    public User(int id, String userName, String hashCode, String salt) {
        this.id = id;
        this.userName = userName;
        this.hashCode = hashCode;
        this.salt = salt;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getSalt() {
        if (this.salt == null) {
            byte[] userSalt = PasswordUtil.getNextSalt();
            setSalt(userName.toString());
        }
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
