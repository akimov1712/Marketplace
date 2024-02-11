package com.fake.marketplace.presentation.screens.detailProduct

import com.denzcoskun.imageslider.models.SlideModel
import com.fake.marketplace.Const
import com.fake.marketplace.databinding.FragmentDetailProductBinding
import com.fake.marketplace.presentation.base.BaseFragment


class DetailProductFragment : BaseFragment<FragmentDetailProductBinding>(FragmentDetailProductBinding::inflate) {

    override fun setViews() {
        super.setViews()

        val listImage = Const.imageWithIdMap["88f88865-ae74-4b7c-9d81-b78334bb97db"]
            ?.map { SlideModel(it) } ?: emptyList()
        binding.imageSlider.setImageList(listImage)
    }
}