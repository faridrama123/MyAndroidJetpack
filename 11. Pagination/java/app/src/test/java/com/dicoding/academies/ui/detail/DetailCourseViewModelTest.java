package com.dicoding.academies.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.dicoding.academies.data.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.CourseWithModule;
import com.dicoding.academies.utils.DataDummy;
import com.dicoding.academies.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailCourseViewModelTest {
    private DetailCourseViewModel viewModel;
    private CourseEntity dummyCourse = DataDummy.generateDummyCourses().get(0);
    private String courseId = dummyCourse.getCourseId();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private AcademyRepository academyRepository;

    @Mock
    private Observer<Resource<CourseWithModule>> observer;

    @Before
    public void setUp() {
        viewModel = new DetailCourseViewModel(academyRepository);
        viewModel.setCourseId(courseId);
    }

    @Test
    public void getCourseWithModule() {
        Resource<CourseWithModule> dummyCourseWithModule = Resource.success(DataDummy.generateDummyCourseWithModules(dummyCourse, true));
        MutableLiveData<Resource<CourseWithModule>> course = new MutableLiveData<>();
        course.setValue(dummyCourseWithModule);

        when(academyRepository.getCourseWithModules(courseId)).thenReturn(course);

        viewModel.courseModule.observeForever(observer);

        verify(observer).onChanged(dummyCourseWithModule);
    }

    @Test
    public void setBookmark() {
        Resource<CourseWithModule> dummyCourseWithModule = Resource.success(DataDummy.generateDummyCourseWithModules(dummyCourse, false));
        MutableLiveData<Resource<CourseWithModule>> course = new MutableLiveData<>();
        course.setValue(dummyCourseWithModule);

        when(academyRepository.getCourseWithModules(courseId)).thenReturn(course);
        viewModel.courseModule.observeForever(observer);

        doNothing().when(academyRepository).setCourseBookmark(dummyCourse, true);

        viewModel.setBookmark();

        verify(academyRepository).setCourseBookmark(dummyCourse, true);
    }
}