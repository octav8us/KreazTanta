package com.example.kreaz.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kreaz.R
import com.example.kreaz.databinding.BranchesFragmentBinding
import com.example.kreaz.ui.main.models.BranchesViewModel

class BranchesFragment : Fragment() {

    private lateinit var binding: BranchesFragmentBinding

    lateinit var recyclerView: RecyclerView
    private val BranchesModel: BranchesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BranchesFragmentBinding.inflate(inflater, container, false)
        binding.apply {

        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        BranchesModel.branches.observe(viewLifecycleOwner, { result ->


            binding.adress1.text = result[0].address
            binding.adress2.text = result[1].address
            binding.adress3.text = result[2].address
            binding.adress4.text = result[3].address
            binding.adress5.text = result[4].address
            binding.adress6.text = result[5].address
            binding.adress7.text = result[6].address

            binding.name1.text = result[0].name
            binding.name2.text = result[1].name
            binding.name3.text = result[2].name
            binding.name4.text = result[3].name
            binding.name5.text = result[4].name
            binding.name6.text = result[5].name
            binding.name7.text = result[6].name

            val imgUri1 = result[0].img.toUri().buildUpon()?.scheme("https")?.build()
            val imgUri2 = result[1].img.toUri().buildUpon()?.scheme("https")?.build()
            val imgUri3 = result[2].img.toUri().buildUpon()?.scheme("https")?.build()
            val imgUri4 = result[3].img.toUri().buildUpon()?.scheme("https")?.build()
            val imgUri5 = result[4].img.toUri().buildUpon()?.scheme("https")?.build()
            val imgUri6 = result[5].img.toUri().buildUpon()?.scheme("https")?.build()
            val imgUri7 = result[6].img.toUri().buildUpon()?.scheme("https")?.build()




            binding.image1.load(imgUri1) {
                placeholder(R.drawable.loading_animation)
            }
            binding.image2.load(imgUri2) {
                placeholder(R.drawable.loading_animation)
            }
            binding.image3.load(imgUri3) {
                placeholder(R.drawable.loading_animation)
            }
            binding.image4.load(imgUri4) {
                placeholder(R.drawable.loading_animation)
            }
            binding.image5.load(imgUri5) {
                placeholder(R.drawable.loading_animation)
            }
            binding.image6.load(imgUri6) {
                placeholder(R.drawable.loading_animation)
            }
            binding.image7.load(imgUri7) {
                placeholder(R.drawable.loading_animation)
            }
            binding.phone1.text = result[0].phones[0]
            binding.phone2.text = result[1].phones[0]
            binding.phone3.text = result[2].phones[0]
            binding.phone4.text = result[3].phones[0]
            binding.phone5.text = result[4].phones[0]
            binding.phone6.text = result[5].phones[0]
            binding.phone7.text = result[6].phones[0]


        })
    }


}