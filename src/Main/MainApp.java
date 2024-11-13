package Main;

import Controller.DottoreController;
import DataBase.UserDAO;
import DataBase.UserDAOimp;

public class MainApp {
	public static void main(String[] args) {
        @SuppressWarnings("rawtypes")
		UserDAO userDAO = new UserDAOimp();
        DottoreController dottoreController = new DottoreController(userDAO);
        dottoreController.mostraLogin();
    }
}

