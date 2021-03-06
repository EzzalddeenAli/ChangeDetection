package com.bernaferrari.changedetection.repo.source.local

import com.bernaferrari.changedetection.repo.AppExecutors
import com.bernaferrari.changedetection.repo.Site
import com.bernaferrari.changedetection.repo.source.SitesDataSource
import io.reactivex.Observable
import kotlinx.coroutines.withContext
import javax.inject.Singleton


/**
 * Concrete implementation of a data source as a db.
 * Inspired from Architecture Components MVVM sample app
 */
@Singleton
class SitesLocalDataSource constructor(
    private val mAppExecutors: AppExecutors,
    private val mSitesDao: SitesDao
) : SitesDataSource {

    override fun getDataWithChanges(): Observable<List<Site>> {
        return mSitesDao.getDataWithChanges()
    }

    override suspend fun getSites(): List<Site> = withContext(mAppExecutors.ioContext) {
        mSitesDao.sites
    }

    override fun getSiteById(siteId: String): Site? = mSitesDao.getSiteById(siteId)

    override suspend fun getSiteByUrl(siteUrl: String): Site? =
        withContext(mAppExecutors.ioContext) {
            mSitesDao.getSiteByUrl(siteUrl)
        }

    override suspend fun saveSite(site: Site) = withContext(mAppExecutors.ioContext) {
        mSitesDao.insertSite(site)
    }

    override suspend fun updateSite(site: Site) = withContext(mAppExecutors.ioContext) {
        mSitesDao.updateSite(site)
    }

    override suspend fun deleteAllSites() = withContext(mAppExecutors.ioContext) {
        mSitesDao.deleteSites()
    }

    override suspend fun deleteSite(siteId: String) = withContext(mAppExecutors.ioContext) {
        mSitesDao.deleteSiteById(siteId)
    }
}
