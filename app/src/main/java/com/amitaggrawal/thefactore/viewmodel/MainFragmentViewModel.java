/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amitaggrawal.thefactore.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.amitaggrawal.thefactore.data.remote.ListMyEntry;
import com.amitaggrawal.thefactore.repository.MyRepository;

import java.util.List;

public class MainFragmentViewModel extends ViewModel {

    private final MyRepository mRepository;
    private final LiveData<List<ListMyEntry>> mData;

    public MainFragmentViewModel(MyRepository repository) {
        mRepository = repository;
        mData = mRepository.getData();
    }

    public LiveData<List<ListMyEntry>> getData() {
        return mData;
    }


}
