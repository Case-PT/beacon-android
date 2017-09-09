package li.brianv.rescueme.view.fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import li.brianv.rescueme.R;
import li.brianv.rescueme.di.components.MainComponent;
import li.brianv.rescueme.presenter.FormPresenter;
import li.brianv.rescueme.util.Log;
import li.brianv.rescueme.util.UIUtil;
import li.brianv.rescueme.view.DefaultTextWatcher;
import li.brianv.rescueme.view.FormView;

public class FormFragment extends BaseFragment implements FormView {
    private static final String LOG_TAG = FormFragment.class.getSimpleName();

    @Inject
    FormPresenter formPresenter;

    @BindView(R.id.form)
    LinearLayout form;

    @BindView(R.id.reporterName)
    EditText reporterName;
    @BindView(R.id.reporterNumber)
    EditText reporterNumber;
    @BindView(R.id.reporterEmail)
    EditText reporterEmail;
    @BindView(R.id.reportedName)
    EditText reportedName;
    @BindView(R.id.reportedNumber)
    EditText reportedNumber;
    @BindView(R.id.reportedEmail)
    EditText reportedEmail;
    @BindView(R.id.reportedAddress)
    EditText reportedAddress;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private Unbinder unbinder;

    @OnClick(R.id.fab)
    public void fabPress() {
        formPresenter.onFabPress();
    }

    @Override
    public void onResume() {
        super.onResume();
        formPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        formPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        formPresenter.destroy();
    }

    public FormFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MainComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_form, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);

        setupEditTextUpdaters();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.formPresenter.setView(this);
    }

    @Override
    public void setupEditTextUpdaters() {
        reporterName.addTextChangedListener(new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                formPresenter.updateReporterName(reporterName.getText().toString());
            }
        });
        reporterNumber.addTextChangedListener(new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                formPresenter.updateReporterNumber(reporterNumber.getText().toString());
            }
        });
        reporterEmail.addTextChangedListener(new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                formPresenter.updateReporterEmail(reporterEmail.getText().toString());
            }
        });
        reportedName.addTextChangedListener(new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                formPresenter.updateReportedName(reportedName.getText().toString());
            }
        });
        reportedNumber.addTextChangedListener(new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                formPresenter.updateReportedNumber(reportedNumber.getText().toString());
            }
        });
        reportedEmail.addTextChangedListener(new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                formPresenter.updateReportedEmail(reportedEmail.getText().toString());
            }
        });
        reportedAddress.addTextChangedListener(new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                formPresenter.updateReportedAddress(reportedAddress.getText().toString());
            }
        });
    }

    @Override
    public void showConfirmFAB() {
        Log.d(LOG_TAG, "Confirm FAB");
        fab.setBackgroundTintList(ColorStateList.valueOf(getContext().getColor(R.color.materialGreen)));
        fab.setImageResource(R.drawable.ic_check_white_24dp);
        fab.setRotation(0);
    }

    @Override
    public void showCancelFAB() {
        Log.d(LOG_TAG, "Cancel FAB");
        fab.setBackgroundTintList(ColorStateList.valueOf(getContext().getColor(R.color.colorAccent)));
        fab.setImageResource(R.drawable.ic_add_white_24dp);
        fab.setRotation(45);
    }

    @Override
    public void showAddFAB() {
        Log.d(LOG_TAG, "Add FAB");
        fab.setBackgroundTintList(ColorStateList.valueOf(getContext().getColor(R.color.colorAccent)));
        fab.setImageResource(R.drawable.ic_add_white_24dp);
        fab.setRotation(0);
    }

    @Override
    public void showForm() {
        form.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideForm() {
        form.setVisibility(View.GONE);
        UIUtil.hideKeyboardFrom(getContext(), form);
    }

    @Override
    public void clearForm() {
        reporterName.getText().clear();
        reporterNumber.getText().clear();
        reporterEmail.getText().clear();
        reportedName.getText().clear();
        reportedNumber.getText().clear();
        reportedEmail.getText().clear();
        reportedAddress.getText().clear();
    }

    @Override
    public boolean formIsShowing() {
        return form.getVisibility() == View.VISIBLE;
    }

    @Override
    public void displaySuccess() {
        showToastMessage("Form submitted");
    }

    @Override
    public void displaySubmitFormFailure() {
        showToastMessage("Error submitting form");
    }
}
