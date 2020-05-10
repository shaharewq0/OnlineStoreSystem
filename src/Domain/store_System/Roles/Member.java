package Domain.store_System.Roles;

import Domain.RedClasses.User;

public class Member implements System_Role {
  //  private Registered registered;
    private User user;
//    public Member(Registered registered) {
//        this.registered = registered;
//    }

    public Member(User user) {
		this.user = user;	}

    public User getUser()
    {
    	return user;
    }
//	public Registered getRegistered() {
//        return registered;
//    }
}
