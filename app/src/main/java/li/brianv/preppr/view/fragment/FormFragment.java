package li.brianv.preppr.view.fragment;

import android.os.Bundle;
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
import li.brianv.preppr.R;
import li.brianv.preppr.di.components.MainComponent;
import li.brianv.preppr.presenter.FormPresenter;
import li.brianv.preppr.util.UIUtil;
import li.brianv.preppr.view.DefaultTextWatcher;
import li.brianv.preppr.view.FormView;

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
    EditText reportedAdress;

    private Unbinder unbinder;

    @BindView(R.id.fab)
    public FloatingActionButton fab;

    @OnClick(R.id.fab)
    public void addRequest(View view) {
        if (form.getVisibility() != View.VISIBLE) {
            form.setVisibility(View.VISIBLE);
            view.setRotation(45);
        } else {
            form.setVisibility(View.GONE);
            view.setRotation(0);
            UIUtil.hideKeyboardFrom(getContext(), form);
        }
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

//        setupEditTextUpdaters();
        return fragmentView;
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
        reportedAdress.addTextChangedListener(new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                formPresenter.updateReportedAddress(reportedAdress.getText().toString());
            }
        });
    }

    @Override
    public void showConfirmFAB() {
        fab.setBackgroundColor(getContext().getColor(R.color.materialGreen));
        fab.setImageResource(R.drawable.ic_check_white_24dp);
        fab.setRotation(0);
    }

    @Override
    public void hideConfirmFAB() {
        fab.setBackgroundColor(getContext().getColor(R.color.colorAccent));
        fab.setImageResource(R.drawable.ic_check_white_24dp);
        fab.setRotation(45);
    }
}
