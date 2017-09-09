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
package li.brianv.preppr.presenter;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import li.brianv.preppr.di.PerActivity;
import li.brianv.preppr.view.FormView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class FormPresenter implements Presenter {
    private FormView formView;

    private String reporterName = "";
    private String reporterNumber = "";
    private String reporterEmail = "";
    private String reportedName = "";
    private String reportedNumber = "";
    private String reportedEmail = "";
    private String reportedAddress = "";

    @Inject
    FormPresenter() {
    }

    public void updateReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public void updateReporterNumber(String reporterNumber) {
        this.reporterNumber = reporterNumber;
    }

    public void updateReporterEmail(String reporterEmail) {
        this.reporterEmail = reporterEmail;
    }

    public void updateReportedName(String reportedName) {
        this.reportedName = reportedName;
    }

    public void updateReportedNumber(String reportedNumber) {
        this.reportedNumber = reportedNumber;
    }

    public void updateReportedEmail(String reportedEmail) {
        this.reportedEmail = reportedEmail;
    }

    public void updateReportedAddress(String reportedAddress) {
        this.reportedAddress = reportedAddress;
    }

    private void onFieldUpdated() {
        if(!reporterName.isEmpty() && !reporterEmail.isEmpty() && !reporterNumber.isEmpty()
                && !reportedName.isEmpty() && !reportedEmail.isEmpty() && !reportedNumber.isEmpty()
                && !reportedAddress.isEmpty())
            formView.showConfirmFAB();
        else
            formView.hideConfirmFAB();
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
//        this.getUserDetailsUseCase.dispose();
        this.formView = null;
    }


}
