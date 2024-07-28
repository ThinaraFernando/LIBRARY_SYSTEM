package entity;

public class MemberEntity {

    private int MemberId;

    private String Name;

    private String Address;
    
    private String Phone;

    public MemberEntity() {

    }

    public MemberEntity(int memberId, String name, String address, String phone) {
        MemberId = memberId;
        Name = name;
        Address = address;
        Phone = phone;
    }

    public int getMemberId() {
        return MemberId;
    }

    public void setMemberId(int memberId) {
        MemberId = memberId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    @Override
    public String toString() {
        return "MemberEntity [MemberId=" + MemberId + ", Name=" + Name + ", Address=" + Address + ", Phone=" + Phone + "]";
    }

    

}
