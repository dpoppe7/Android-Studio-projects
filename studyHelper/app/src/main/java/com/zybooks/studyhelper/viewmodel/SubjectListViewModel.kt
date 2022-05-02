package com.zybooks.studyhelper.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.zybooks.studyhelper.model.Subject
import com.zybooks.studyhelper.repo.StudyRepository

class SubjectListViewModel(application: Application) : AndroidViewModel(application) {

    private val studyRepo = StudyRepository.getInstance(application.applicationContext)

    val subjectListLiveData: LiveData<List<Subject>> = studyRepo.getSubjects()

    fun addSubject(subject: Subject) = studyRepo.addSubject(subject)

    fun deleteSubject(subject: Subject) = studyRepo.deleteSubject(subject)
}

/*
package com.zybooks.studyhelper.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.zybooks.studyhelper.model.Subject
import com.zybooks.studyhelper.repo.StudyRepository

enum class SubjectSortOrder {
    ALPHABETIC, NEW_FIRST, OLD_FIRST
}

class SubjectListViewModel(application: Application) : AndroidViewModel(application) {

    private val studyRepo = StudyRepository.getInstance(application.applicationContext)

    private val subjectSortOrderLiveData = MutableLiveData<SubjectSortOrder>()

    val subjectListLiveData: LiveData<List<Subject>> =
        Transformations.switchMap(subjectSortOrderLiveData) { sortOrder ->
            when (sortOrder) {
                SubjectSortOrder.OLD_FIRST -> studyRepo.getSubjectsOldestFirst()
                SubjectSortOrder.NEW_FIRST -> studyRepo.getSubjectsNewestFirst()
                else -> studyRepo.getSubjects()
            }
        }

    fun setSortOrder(sortOrder: SubjectSortOrder) {
        subjectSortOrderLiveData.value = sortOrder
    }

    fun addSubject(subject: Subject) = studyRepo.addSubject(subject)

    fun deleteSubject(subject: Subject) = studyRepo.deleteSubject(subject)
}*/
