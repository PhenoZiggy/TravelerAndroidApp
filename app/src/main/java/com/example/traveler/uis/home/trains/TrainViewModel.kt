    package com.example.traveler.uis.home.trains

    import androidx.lifecycle.ViewModel
    import com.example.traveler.data.repositories.ReservationRepository
    import com.example.traveler.data.repositories.TrainRepository
    import com.example.traveler.data.repositories.UserRepository
    import com.example.traveler.util.ApiException
    import com.example.traveler.util.Coroutines
    import com.example.traveler.util.NoInternetException
    import com.example.traveler.util.lazyDeferred

    class TrainViewModel(
        repository: TrainRepository,
        private val reservationRepo: ReservationRepository,
        private val userRepo: UserRepository
    ): ViewModel() {
        val user = userRepo.getUser()
        var nic: String? = null
        init {
            user.observeForever { newUser ->
                // Check if newUser is not null and has a nic value
                if (newUser != null) {
                    nic = newUser.nic
                }}
        }
        fun bookTrain(trainId : String) {
            Coroutines.main {
                try {
                    reservationRepo.reserveAtrain(trainId , nic!!, "0")
                }catch (e : ApiException){

                }catch (e : NoInternetException){

                }
            }
        }

        val trains by lazyDeferred {
            repository.getTrains()
        }
    }