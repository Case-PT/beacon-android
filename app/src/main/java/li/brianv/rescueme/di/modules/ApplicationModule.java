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
package li.brianv.rescueme.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import li.brianv.data.executor.JobExecutor;
import li.brianv.data.repository.FormDataRepository;
import li.brianv.data.repository.MapDataRepository;
import li.brianv.domain.executor.PostExecutionThread;
import li.brianv.domain.executor.ThreadExecutor;
import li.brianv.domain.repository.FormRepository;
import li.brianv.domain.repository.MapRepository;
import li.brianv.rescueme.AndroidApplication;
import li.brianv.rescueme.UIThread;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

//  @Provides @Singleton UserCache provideUserCache(UserCacheImpl userCache) {
//    return userCache;
//  }

    @Provides
    @Singleton
    MapRepository provideRecipeRepository(MapDataRepository mapDataRepository) {
        return mapDataRepository;
    }

    @Provides
    @Singleton
    FormRepository provideFormRepository(FormDataRepository formDataRepository) {
        return formDataRepository;
    }
}
