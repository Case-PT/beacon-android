package li.brianv.rescueme.view;

public interface FormView {
    void setupEditTextUpdaters();

    void showConfirmFAB();

    void hideConfirmFAB();

    void showCancelFAB();

    void showAddFAB();

    void showForm();

    void hideForm();

    void clearForm();


    boolean formIsShowing();

    void displaySuccess();

    void displayMessage(String message);

    void displaySubmitFormFailure();
}
