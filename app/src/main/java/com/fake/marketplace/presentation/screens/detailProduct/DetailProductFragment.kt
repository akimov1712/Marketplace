package com.fake.marketplace.presentation.screens.detailProduct

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.models.SlideModel
import com.fake.marketplace.Const
import com.fake.marketplace.Const.MAX_LINES_TEXT
import com.fake.marketplace.Const.MIN_LINES_TEXT
import com.fake.marketplace.Const.imageWithIdMap
import com.fake.marketplace.R
import com.fake.marketplace.databinding.FragmentDetailProductBinding
import com.fake.marketplace.domain.entities.product.ProductEntity
import com.fake.marketplace.presentation.base.BaseFragment
import com.fake.marketplace.presentation.screens.detailProduct.adapter.CharactAdapter
import com.fake.marketplace.utils.productCountOrderQuantityString
import com.fake.marketplace.utils.setTerminationFeedbackCountString
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailProductFragment : BaseFragment<FragmentDetailProductBinding>(FragmentDetailProductBinding::inflate) {

    private val args by navArgs<DetailProductFragmentArgs>()
    private val viewModel by viewModels<DetailProductViewModel>()
    private val adapter by lazy { CharactAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProduct()
    }

    private fun getProduct(){
        viewModel.getProduct(args.id)
    }

    override fun setAdapters() {
        super.setAdapters()
        binding.rvCharact.adapter = adapter
    }

    override fun setListenersInView() {
        super.setListenersInView()
        with(binding){
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnHideDesc.setOnClickListener {
                llInfoDescr.visibility = View.GONE
                btnShowDesc.visibility = View.VISIBLE
            }
            btnShowDesc.setOnClickListener {
                llInfoDescr.visibility = View.VISIBLE
                btnShowDesc.visibility = View.GONE
            }
            tvHideShowComposition.setOnClickListener {
                val lines = tvComposition.maxLines
                if (lines == MIN_LINES_TEXT){
                    tvComposition.maxLines = MAX_LINES_TEXT
                    tvHideShowComposition.text = "Скрыть"
                } else {
                    tvComposition.maxLines = MIN_LINES_TEXT
                    tvHideShowComposition.text = "Подробнее"
                }
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        with(binding){
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED){
                    viewModel.state.collect{
                        when(it){
                            is DetailProductState.Product -> {
                                val product = it.item
                                setupImageSlider(product)
                                setupBtnFavorite(product)
                                setupNameAndPrice(product)
                                setupRating(product)
                                setupDescr(product)
                                tvComposition.text = product.ingredients
                                adapter.submitList(product.infoList)
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }

    private fun setupDescr(product: ProductEntity) {
        binding.tvBtnBrand.text = product.title
        binding.tvDescr.text = product.description
    }

    private fun setupRating(product: ProductEntity) {
        val rating = product.feedback.rating
        binding.ratingBar.rating = rating
        binding.tvRatingWithCountFeedback.text = "$rating · " +
                "${product.feedback.count.setTerminationFeedbackCountString()}"
    }

    private fun setupNameAndPrice(
        product: ProductEntity,
    ) {
        val currency = product.price.unit
        binding.tvOldPrice.text = "${product.price.price} $currency"
        binding.tvOldPriceButton.text = "${product.price.price} $currency"
        binding.tvPrice.text = "${product.price.priceWithDiscount} $currency"
        binding.tvPriceButton.text = "${product.price.priceWithDiscount} $currency"
        binding.tvDiscount.text = "-${product.price.discount} %"
        binding.tvTitle.text = "${product.title}"
        binding.tvSubtitle.text = "${product.subTitle}"
        binding.tvAvailable.text = "Доступно для заказа  " +
                "${product.available.productCountOrderQuantityString()}"
    }

    private fun setupBtnFavorite(product: ProductEntity) {
        val stateImageFavorite = if (product.isFavorite) R.drawable.ic_heart_fill
        else R.drawable.ic_heart_stroke
        binding.btnFavorite.setImageResource(stateImageFavorite)
        binding.btnFavorite.setOnClickListener {
            viewModel.updateFavorite(product.id, !product.isFavorite)
        }
    }

    private fun setupImageSlider(product: ProductEntity) {
        val imageSliderList = imageWithIdMap[product.id]
            ?.map { SlideModel(it) } ?: emptyList()
        binding.imageSlider.setImageList(imageSliderList)
    }

}