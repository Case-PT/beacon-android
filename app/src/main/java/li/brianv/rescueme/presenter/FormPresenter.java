/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package li.brianv.rescueme.presenter;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import li.brianv.domain.Form;
import li.brianv.domain.interactor.DefaultObserver;
import li.brianv.domain.interactor.SubmitForm;
import li.brianv.rescueme.di.PerActivity;
import li.brianv.rescueme.util.Log;
import li.brianv.rescueme.view.FormView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class FormPresenter implements Presenter {
    private static final String LOG_TAG = FormPresenter.class.getSimpleName();
    private FormView formView;

    private String reporterName = "";
    private String reporterNumber = "";
    private String reporterEmail = "";
    private String reportedName = "";
    private String reportedNumber = "";
    private String reportedEmail = "";
    private String reportedAddress = "";

    private boolean fieldsFilled = false;

    private final SubmitForm submitForm;

    @Inject
    FormPresenter(SubmitForm submitForm) {
        this.submitForm = submitForm;
    }

    public void updateReporterName(String reporterName) {
        this.reporterName = reporterName;
        onFieldUpdated();
    }

    public void updateReporterNumber(String reporterNumber) {
        this.reporterNumber = reporterNumber;
        onFieldUpdated();
    }

    public void updateReporterEmail(String reporterEmail) {
        this.reporterEmail = reporterEmail;
        onFieldUpdated();
    }

    public void updateReportedName(String reportedName) {
        this.reportedName = reportedName;
        onFieldUpdated();
    }

    public void updateReportedNumber(String reportedNumber) {
        this.reportedNumber = reportedNumber;
        onFieldUpdated();
    }

    public void updateReportedEmail(String reportedEmail) {
        this.reportedEmail = reportedEmail;
        onFieldUpdated();
    }

    public void updateReportedAddress(String reportedAddress) {
        this.reportedAddress = reportedAddress;
        onFieldUpdated();
    }

    public void onFabPress() {
        if (formView.formIsShowing()) {
            formView.hideForm();
            formView.hideConfirmFAB();
            formView.showAddFAB();
        } else {
            if (fieldsFilled){
                formView.showConfirmFAB();
            }
            formView.showForm();
            formView.showCancelFAB();
        }

    }

    public void onConfirmPress() {
        submitForm.execute(new SubmitFormObserver(),
                SubmitForm.Params.forForm(new Form(reporterName,
                        reporterNumber, reporterEmail,
                        reportedName, reportedNumber,
                        reportedEmail, reportedAddress)));
    }

    private void onFieldUpdated() {
        if (!reporterName.isEmpty() && !reporterEmail.isEmpty() && !reporterNumber.isEmpty()
                && !reportedName.isEmpty() && !reportedEmail.isEmpty() && !reportedNumber.isEmpty()
                && !reportedAddress.isEmpty()) {
            formView.showConfirmFAB();
            fieldsFilled = true;
        } else {
            formView.hideConfirmFAB();
            fieldsFilled = false;
        }
    }

    public void setView(@NonNull FormView view) {
        this.formView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.submitForm.dispose();
        this.formView = null;
    }

    private class SubmitFormObserver extends DefaultObserver<Object> {
        @Override
        public void onNext(Object o) {
            Log.d(LOG_TAG, o.toString());
        }

        @Override
        public void onComplete() {
            completeForm();
        }

        @Override
        public void onError(Throwable exception) {
            completeForm();
        }

        private void completeForm() {
            formView.clearForm();
            formView.displaySuccess();
            formView.hideForm();
            formView.showAddFAB();
        }
    }
}
