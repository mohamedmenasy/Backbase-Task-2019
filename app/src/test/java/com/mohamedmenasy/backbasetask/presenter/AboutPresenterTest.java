package com.mohamedmenasy.backbasetask.presenter;

import android.content.Context;

import com.mohamedmenasy.backbasetask.features.about.About;
import com.mohamedmenasy.backbasetask.features.about.AboutInfo;
import com.mohamedmenasy.backbasetask.features.about.AboutPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class AboutPresenterTest {

    @Mock
    Context context;
    @Mock
    About.View view;
    private AboutPresenterImpl presenter;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new AboutPresenterImpl(view, context, null);
    }

    @Test
    public void checkGetAboutInfo() {
        presenter.getAboutInfo();
        verify(view, times(1)).showProgress();
    }

    @Test
    public void checkOnSuccess() {
        presenter.onSuccess(new AboutInfo());
        verify(view, times(1)).hideProgress();
    }

    @Test
    public void checkOnFail() {
        presenter.onFail();
        verify(view, times(1)).hideProgress();
        verify(view, times(1)).showError();
    }
}
