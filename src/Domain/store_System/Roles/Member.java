package Domain.store_System.Roles;

public class Member implements System_Role {
    private Registered registered;

    public Member(Registered registered) {
        this.registered = registered;
    }

    public Registered getRegistered() {
        return registered;
    }
}
