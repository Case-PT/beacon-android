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

    void displaySubmitFormFailure();

    void displayUserLocation(String message);
}
