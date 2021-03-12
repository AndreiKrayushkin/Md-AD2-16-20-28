package by.andrei.firstproject.homework_7_1_rxjava.data

import android.content.Context
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class Repository (context: Context){
    private val database = CarDatabase.getDatabase(context)

    fun getCarList(): Single<List<Car>> = Single.create<List<Car>> {
            val carList = database.getCarDAO().getCarsList()
            it.onSuccess(carList)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun getCar(carId: Int) : Car {
        return Single.create<Car> {
            val carItem = database.getCarDAO().getCar(carId)
            it.onSuccess(carItem)
        }.subscribeOn(Schedulers.io())
                .blockingGet()
    }

    fun insertAll(car: Car) {
        Single.create<Car> {
            database.getCarDAO().insertAll(car)
        }.subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun updateCar(car: Car) {
        Single.create<Car>{
            database.getCarDAO().update(car)
        }.subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun deleteCar(car:Car) {
        Single.create<Car> {
            database.getCarDAO().delete(car)
        }.subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun getWorkList(): Single<List<Work>> = Single.create<List<Work>> {
            val workList = database.getWorkDAO().getWorkList()
            it.onSuccess(workList)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun getWork(workId: Int) : Work {
        return Single.create<Work> {
            val workItem = database.getWorkDAO().getWork(workId)
            it.onSuccess(workItem)
        }.subscribeOn(Schedulers.io())
                .blockingGet()
    }

    fun getWorkFromParentsCar(parentCar: String?): Single<List<Work>> = Single.create<List<Work>> {
            val work = database.getWorkDAO().getWorkFromParentsCar(parentCar)
            it.onSuccess(work)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun insertAll(work: Work) {
        Single.create<Work> {
            database.getWorkDAO().insertAll(work)
        }.subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun update(work: Work) {
        Single.create<Work> {
            database.getWorkDAO().update(work)
        }.subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun delete(work: Work) {
        Single.create<Work> {
            database.getWorkDAO().delete(work)
        }.subscribeOn(Schedulers.io())
                .subscribe()
    }
}