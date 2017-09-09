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
package li.brianv.preppr.di.components;

import dagger.Component;
import li.brianv.preppr.di.PerActivity;
import li.brianv.preppr.di.modules.ActivityModule;
import li.brianv.preppr.di.modules.MainModule;
import li.brianv.preppr.view.fragment.FormFragment;
import li.brianv.preppr.view.fragment.MapFragment;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MainModule.class})
public interface MainComponent extends ActivityComponent {
    void inject(MapFragment mapFragment);

    void inject(FormFragment formFragment);
}
