package com.finwin.doorstep.digicob.logout_listner;

public interface LogoutListener {
    void onSessionLogout();
    void doLogout();
    void background();
    void foreground();
}
