package li.brianv.rescueme.view;

public interface FormView {
    void setupEditTextUpdaters();

    void showConfirmFAB();

    void hideConfirmFAB();

    void showForm();

    void hideForm();

    boolean formIsShowing();

    void displaySuccess();
}
