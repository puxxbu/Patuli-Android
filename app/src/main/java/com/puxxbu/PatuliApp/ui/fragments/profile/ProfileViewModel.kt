package com.puxxbu.PatuliApp.ui.fragments.profile


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puxxbu.PatuliApp.data.api.response.profile.EditPasswordResponse
import com.puxxbu.PatuliApp.data.api.response.profile.ProfileResponse
import com.puxxbu.PatuliApp.data.model.UserDataModel
import com.puxxbu.PatuliApp.data.repository.DataRepository
import com.puxxbu.PatuliApp.utils.Event
import kotlinx.coroutines.launch

class ProfileViewModel(private val dataRepository: DataRepository) : ViewModel() {
    val profileData : LiveData<ProfileResponse> = dataRepository.profileResponse
    val responseMessage: LiveData<Event<String>> = dataRepository.responseMessage
    val isLoading: LiveData<Boolean> = dataRepository.isLoading
    val editPasswordResponse: LiveData<Event<EditPasswordResponse>> = dataRepository.editPasswordResponse

    fun getSessionData(): LiveData<UserDataModel> = dataRepository.getSessionData()

    fun changePassword(token: String, oldPassword: String, newPassword: String, newPasswordConfirmation: String )  {
        viewModelScope.launch {
            dataRepository.editPassword(token, oldPassword, newPassword, newPasswordConfirmation)
        }
    }

    fun getProfile(token : String) = dataRepository.getProfile(token)
}
