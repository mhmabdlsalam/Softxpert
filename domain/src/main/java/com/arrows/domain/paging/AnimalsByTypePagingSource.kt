package com.arrows.domain.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arrows.domain.model.Animal
import com.arrows.domain.repo.Repo
import com.arrows.domain.usecase.GetAnimals

class AnimalsByTypePagingSource(private val repo: Repo,private val type:String) : PagingSource<Int, Animal>() {
    override fun getRefreshKey(state: PagingState<Int, Animal>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Animal> {
        return try {
            val pageNumber = params.key ?: 1
            Log.d("hesham","page result "+pageNumber)

            val result = repo.getAnimalsByType(pageNumber,type)
            Log.d("hesham","page result"+result.toString())
            LoadResult.Page(
                data = result,
                prevKey = if (pageNumber==1)null else pageNumber-1,
                nextKey = if (result.isNullOrEmpty())null else pageNumber+1
            )
        } catch (e: Exception) {
            Log.e("hesham","page error"+e.message,e)
            LoadResult.Error(e)


        }
    }


}