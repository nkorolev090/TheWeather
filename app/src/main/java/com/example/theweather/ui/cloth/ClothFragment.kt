import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theweather.R
import com.example.theweather.databinding.FragmentClothesBinding
import com.example.theweather.ui.cloth.MyAdapter

class ClothesFragment : Fragment() {

    private var _binding: FragmentClothesBinding? = null
//    private lateinit var sharedViewModel: SharedViewModel

    private lateinit var adapter: MyAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var imageId: Array<Int>
    private lateinit var heading: Array<String>
    private lateinit var news: Array<String>

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClothesBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = MyAdapter(imageId, heading, news)
        recyclerView.adapter = adapter
//
//        sharedViewModel.temperature.observe(viewLifecycleOwner, Observer { temp ->
//            updateClothesRecommendation(temp)
//        })
    }

    private fun dataInitialize() {
        imageId = arrayOf(R.drawable.a, R.drawable.b) // Инициализация с placeholder
        heading = arrayOf("", "")
        news = arrayOf("", "")
    }

    private fun updateClothesRecommendation(temp: Float) {
        when {
            temp >= -10 && temp <= 0 -> {
                imageId = arrayOf(
                    R.drawable.winterman1,
                    R.drawable.boys_winter1,
                    R.drawable.winter_woman1,
                    R.drawable.girls_winter1,
                    R.drawable.winter_woman2,
                    R.drawable.boys_winter2,

                    R.drawable.winter_woman2,
                    R.drawable.girls_winter2,
                    R.drawable.winterman3,
                    R.drawable.boys_winter3,
                    R.drawable.winter_woman3,
                    R.drawable.girls_winter3,

                    R.drawable.winterman4,
                    R.drawable.boys_winter4,
                    R.drawable.winter_woman4,
                    R.drawable.girls_winter4,
                    R.drawable.winterman5,
                    R.drawable.boys_winter5,

                    R.drawable.winterman5,
                    R.drawable.girls_winter5,
                    R.drawable.winterman6,
                    R.drawable.boys_winter6,
                    R.drawable.winter_woman6,
                    R.drawable.girls_winter6,
                )
                heading = arrayOf(
                    "Стиль: кежуал",
                    "Стиль: кежуал",//
                    "Стиль: кежуал",
                    "Стиль: нежный и уютный",//
                    "Стиль: классический",
                    "Стиль: элегантный и сдержанный",//

                    "Стиль: повседневный",
                    "Стиль: игривый и уютный",//
                    "Стиль: кежуал",
                    "Стиль: спортивный",//
                    "Стиль: спортивный",
                    "Стиль: элегантный",//

                    "Стиль: смарт-кэжуал",
                    "Стиль: кежуал",//
                    "Стиль: смарт-кэжуал",
                    "Стиль: традиционный и комфортный",//
                    "Cтиль: кежуал",
                    "Стиль: современный",//


                    "Стиль: спортивный элегант  ",
                    "Стиль: современный и модный",//
                    "Cтиль: cпортивный",
                    "Стиль: удобный и практичный",//
                    "Стиль: уличный ",
                    "Стиль: кэжуал",//


                )
                news = arrayOf(
                    "Этот образ – настоящая находка для переменчивой погоды! В нём вам будет комфортно гулять по парку под легким снегом или встречаться с друзьями за чашечкой кофе. Сочетайте удобство и практичность – и будьте готовы к любым сюрпризам природы",
                    "Яркая желтая куртка мгновенно привлекает внимание и создает позитивное настроение в холодные дни. Темно-синие джинсы в сочетании с горчичными ботинками придают образу гармонию, а темно-синяя шапка добавляет уютный акцент. Этот наряд станет отличным выбором для зимних прогулок по парку или веселых встреч с друзьями на свежем воздухе.",//
                    "Зеленый дутый пуховик, черные широкие брюки и белый лонгслив создают яркий и современный образ. Такой комплект отлично подойдет для прогулки по городу или встречи с друзьями в кафе. Комфорт и тепло обеспечат вас в прохладный день.",
                    "Белый комбинезон из овчины обеспечивает максимальное тепло и уют, а бело-коричневые ботинки добавляют стильный акцент. Тёплая белая шапка с ушками завершает образ, придавая ему милый и веселый вид. Этот комплект идеально подходит для зимних семейных выездов на природу или для уютных прогулок по парку, где ваша девочка сможет наслаждаться зимними радостями.",//
                    "Безупречно сдержанный комплект станет вашим верным спутником на работе или официальных встречах. Нейтральные цвета добавят строгости вашему образу. Этот стиль никогда не выйдет из моды!",
                    "Черная куртка с мехом изысканно дополняет образ, создавая ощущение тепла и роскоши. Темно-синие брюки в сочетании с серой водолазкой придают комплекту стильный и современный вид. Подходящий выбор для зимних встреч с семьей или уютных вечеров, когда важен комфорт и элегантность.",//

                    "Темно-синие джинсы трубы, бежевый свитер, коричневый пуховик и ботинки — идеальное сочетание для уютного зимнего образа. Этот наряд прекрасно подойдет для шопинга или неспешной прогулки по парку, когда хочется насладиться зимней атмосферой и уютом",
                    "Светло-серая куртка с заячьими ушками придаёт этому комплекту веселый вид, а белые штаны создают ощущение свежести и легкости. Белые дутики на липучках обеспечивают комфорт и тепло, позволяя вашей девочке свободно бегать и играть. Этот наряд отлично подойдёт для зимних прогулок по заснеженным улицам или для весёлых игр с друзьями на свежем воздухе." ,//
                    "Удобство и красота – идеальное сочетание для повседневной жизни! Этот образ поможет вам выглядеть модно, не жертвуя комфортом. Сочетайте разные текстуры и яркие акценты, и будете выглядеть отлично на любой встрече или прогулке.",
                    "Приглушенно-зеленая дутая куртка сочетает в себе стиль и практичность, а белые спортивные штаны с надписью добавляют динамики и молодежного духа. Белые кроссовки обеспечивают легкость и комфорт, позволяя активно двигаться. Такой комплект идеально подходит для игр на улице или посещения спортивных мероприятий, где можно проявить свою энергичность.",//
                    "Укороченная серая куртка, черные брюки и белый лонгслив — это функциональный и стильный комплект для активных зимних дней. Он отлично подходит для посещения фитнеса или утренней пробежки по свежему воздуху. Легкость и комфорт позволят вам быть на высоте!",
                    "Черный комбинезон с оранжевыми и кремовыми узорами создает эффектный контраст, а капюшон с мехом добавляет нотку роскоши и тепла. Этот комплект идеально подходит для зимних прогулок по городу или для посещения зимних мероприятий, таких как каток или рождественская ярмарка. В нём ваша девочка будет выглядеть как настоящая модница, привлекая восхищенные взгляды окружающих.",//

                    "Ощущение расслабленности и современности – вот что предлагает этот комплект. Его универсальность поможет вам легко адаптировать образ под любой случай, будь то неспешная прогулка или встреча с друзьями. Однако, не советуем одевать этот образ в дождливый день, чтобы не испачкать светлые вещи",
                    "Белая куртка из овчины с зелеными вставками и капюшоном создает уютный и стильный образ, в то время как темно-серый спортивный костюм обеспечивает необходимый комфорт. Этот комплект станет отличным выбором для зимних прогулок или дружеских встреч, позволяя наслаждаться зимними забавами с комфортом.",//
                    "Черное пальто, черная рубашка, коричневые брюки и черные сапоги на каблуке — это элегантный и утонченный образ для деловых встреч или вечерних выходов. Такой комплект подчеркивает вашу серьезность и вкус, идеально подходя для ужина в ресторане или стильной выставки.",
                    "Белый комбинезон из овчины обеспечивает максимальное тепло и уют, а бело-коричневые ботинки добавляют стильный акцент. Тёплая белая шапка с ушками завершает образ, придавая ему милый и веселый вид. Этот комплект идеально подходит для зимних семейных выездов на природу или для уютных прогулок по парку, где ваша девочка сможет наслаждаться зимними радостями.",//
                    "Сдержанность и раслабленность сделают ваш образ запоминающимся. Такой комплект будет уместен в любой будний день  на работе или учебе, подчеркивая ваш утонченный вкус. К белому лонгсливу и черным брюкам отлично подойдут любые украшения, что поможет сделать образ интереснее.",
                    "Черная куртка из овчины с текстурными вставками придает этому комплекту уникальность и стиль, а широкие черные джинсы создают комфортный и расслабленный вид. Этот наряд идеально подойдет для городских прогулок или для посещения зимних мероприятий, где ваш мальчик будет выглядеть как истинный модник, готовый к любым приключениям.",//


                    "Серый спортивный костюм, черный длинный пуховик и черные ботинки — это отличный выбор для активного отдыха. Вы сможете комфортно провести время на свежем воздухе или отправиться на прогулку в парке, оставаясь стильной и теплой. ",
                    "Длинный пуховик металлического цвета привлекает внимание и подчеркивает индивидуальность, а теплые наушники обеспечивают комфорт в холодную погоду. Светло-кремовые ботинки завершают образ, придавая ему лёгкость и стиль. Этот комплект идеально подходит для зимних приключений в городе, будь то поход в кафе с друзьями или прогулка по заснеженным улицам, где ваша девочка будет чувствовать себя уверенно и стильно.",//
                    "Это идеальный комплект в расслабленном стиле для тех, кто ценит свободу и удобство в сочетании со спортивными элементами гардероба.",
                    "Темно-зеленые штаны карго обеспечивают функциональность и стиль, а куртка с мехом добавляет тепла и уюта. Клетчатая рубашка придаёт образу нотку классики и оригинальности. Этот комплект отлично подойдет для зимних походов на природу или для активных игр с друзьями, где ваш мальчик сможет проявить свою индивидуальность и стиль.",//
                    "Белый длинный пуховик, синие широкие джинсы, черные кроссовки и черный свитшот с надписью создадут непринужденный и модный образ. Такой комплект отлично подойдет для городской прогулки, встречи с друзьями или культурного мероприятия, добавляя вам уверенности и комфорта.",
                    "Этот комплект включает в себя белую водолазку, черные брюки и стильные ботинки, дополненные бежевым длинным пуховиком — идеальный образ для зимних приключений. Облачившись в такой наряд, ваш ребенок будет готов к прогулкам в парке, посещению музея или поездке с классом с друзьями. Уютный пуховик обеспечит тепло и защиту от холода, позволяя наслаждаться зимними радостями без забот!",//
                )
            }

            temp >= -20 && temp <= -10  -> {
                imageId = arrayOf(
                    R.drawable.win_man1,
                    R.drawable.boys_winter1,
                    R.drawable.win_woman1,
                    R.drawable.girls_winter1,
                    R.drawable.win_man2,
                    R.drawable.boys_winter2,

                    R.drawable.win_woman2,
                    R.drawable.girls_winter2,
                    R.drawable.win_man3,
                    R.drawable.boys_winter3,
                    R.drawable.win_woman3,
                    R.drawable.girls_winter3,

                    R.drawable.win_man4,
                    R.drawable.boys_winter4,
                    R.drawable.win_woman4,
                    R.drawable.girls_winter4,
                    R.drawable.win_man5,
                    R.drawable.boys_winter5,

                    R.drawable.win_woman5,
                    R.drawable.girls_winter5,
                    R.drawable.win_man6,
                    R.drawable.boys_winter6,
                    R.drawable.win_woman6,
                    R.drawable.girls_winter6,
                )

                heading = arrayOf(
                    "Стиль: кэжуал",
                    "Стиль: кежуал",//
                    "Стиль: кэжуал",
                    "Стиль: нежный и уютный",//
                    "Стиль: уличный",
                    "Стиль: элегантный и сдержанный",//

                    "Стиль: спортивный ",
                    "Стиль: игривый и уютный",//
                    "Стиль: смарт-кэжуал",
                    "Стиль: спортивный",//
                    "Стиль: элегантный",
                    "Стиль: элегантный",//

                    "Стиль: спортивный",
                    "Стиль: кежуал",//
                    "Стиль: классика",
                    "Стиль: традиционный и комфортный",//
                    "Стиль: авиаториальный ",
                    "Стиль: современный",//


                    "Стиль: шик",
                    "Стиль: современный и модный",//
                    "Cтиль: винтажный",
                    "Шапка",//
                    "Стиль: уличный шик ",
                    "Стиль: кэжуал",//


                )
                news = arrayOf(
                    "Серый кардиган в сочетании с черными брюками и пуховиком создаст стильный, но в то же время уютный образ. Это отличный вариант для неофициальных встреч с друзьями или прогулок по городу.",
                    "Яркая желтая куртка мгновенно привлекает внимание и создает позитивное настроение в холодные дни. Темно-синие джинсы в сочетании с горчичными ботинками придают образу гармонию, а темно-синяя шапка добавляет уютный акцент. Этот наряд станет отличным выбором для зимних прогулок по парку или веселых встреч с друзьями на свежем воздухе.",//
                    "Черные джинсы, черные ботинки и темно-коричневая куртка-парка — это практичный и уютный образ для зимних прогулок по городу. Такой комплект идеально подойдет для отдыха с друзьями или семейной прогулки в парке. Плюс такого наряда в том, что он подходит для разных случаев и легко комбинируется с аксессуарами.",
                    "Белый комбинезон из овчины обеспечивает максимальное тепло и уют, а бело-коричневые ботинки добавляют стильный акцент. Тёплая белая шапка с ушками завершает образ, придавая ему милый и веселый вид. Этот комплект идеально подходит для зимних семейных выездов на природу или для уютных прогулок по парку, где ваша девочка сможет наслаждаться зимними радостями.",//
                    "Леопардовая шуба добавит вашему образу дерзости и индивидуальности, в то время как черные карго и водолазка обеспечат комфорт. Такой комплект идеально подходит для вечеринок с друзьями или походов по модным магазинам. С другой стороны, помните, что в условиях сильного снега или дождя шершавая поверхность шубы может легко испачкаться",
                    "Черная куртка с мехом изысканно дополняет образ, создавая ощущение тепла и роскоши. Темно-синие брюки в сочетании с серой водолазкой придают комплекту стильный и современный вид. Подходящий выбор для зимних встреч с семьей или уютных вечеров, когда важен комфорт и элегантность.",//

                    "Желтый комбинезон за город и дутики создают яркий и практичный образ для зимних активностей. Этот наряд станет отличным выбором для катания на лыжах, сноуборде или просто для отдыха на свежем воздухе. В дополнение, комбинезон обеспечивает отличную защиту от холода благодаря его плотной конструкции.",
                    "Светло-серая куртка с заячьими ушками придаёт этому комплекту веселый вид, а белые штаны создают ощущение свежести и легкости. Белые дутики на липучках обеспечивают комфорт и тепло, позволяя вашей девочке свободно бегать и играть. Этот наряд отлично подойдёт для зимних прогулок по заснеженным улицам или для весёлых игр с друзьями на свежем воздухе." ,//
                    "Черные широкие джинсы и шуба из искусственного меха создают удачный и стильный образ. Белая футболка добавляет свежести, и такой наряд подходит для уютных встреч с друзьями или культурных мероприятий.",
                    "Приглушенно-зеленая дутая куртка сочетает в себе стиль и практичность, а белые спортивные штаны с надписью добавляют динамики и молодежного духа. Белые кроссовки обеспечивают легкость и комфорт, позволяя активно двигаться. Такой комплект идеально подходит для игр на улице или посещения спортивных мероприятий, где можно проявить свою энергичность.",//
                    "Черный пуховик, белые брюки и черные ботинки на каблуке создают стильный контраст и подчеркивают вашу утонченность. Этот комплект отлично подойдет для вечеринки или романтической встречи в уютном ресторане. Однако в светлых брюках стоит избегать погоды с осадками — они могут быстро испачкаться. Этот образ сочетает комфорт и изысканность, позволяя чувствовать себя уверенно в любой ситуации.",
                    "Черный комбинезон с оранжевыми и кремовыми узорами создает эффектный контраст, а капюшон с мехом добавляет нотку роскоши и тепла. Этот комплект идеально подходит для зимних прогулок по городу или для посещения зимних мероприятий, таких как каток или рождественская ярмарка. В нём ваша девочка будет выглядеть как настоящая модница, привлекая восхищенные взгляды окружающих.",//

                    "Серый спортивный костюм и черные дутики – идеальный выбор для активного времяпровождения на свежем воздухе. Такой комплект отлично подходит для утренних пробежек или походов на фитнес.",
                    "Белая куртка из овчины с зелеными вставками и капюшоном создает уютный и стильный образ, в то время как темно-серый спортивный костюм обеспечивает необходимый комфорт. Этот комплект станет отличным выбором для зимних прогулок или дружеских встреч, позволяя наслаждаться зимними забавами с комфортом.",//
                    "Укороченная кремовая шуба, кремовый свитер, юбка средней длины и ботильоны выглядят очень элегантно, модно и дорого. Этот наряд будет уместен на зимней вечеринке или культурном мероприятии. Шуба обладает отличными теплоизоляционными свойствами, позволяя вам оставаться в тепле даже в самые холодные дни",
                    "Белый комбинезон из овчины обеспечивает максимальное тепло и уют, а бело-коричневые ботинки добавляют стильный акцент. Тёплая белая шапка с ушками завершает образ, придавая ему милый и веселый вид. Этот комплект идеально подходит для зимних семейных выездов на природу или для уютных прогулок по парку, где ваша девочка сможет наслаждаться зимними радостями.",//
                    "Черная куртка авиатор и серые брюки создают мужественный образ, который подойдет для прогулок по городу или встреч с друзьями в кафе. Черная водолазка добавляет стиля и тепла.",
                    "Шапка",//


                    "Коричневая шуба из овчины, черная водолазка, черные брюки и черные лодочки — это образ, в котором вы будете выглядеть стильно и изысканно на любом событии. Такой комплект подойдет для бизнес-встречи или ужина в ресторане. Плюс этого образа в универсальности — его можно легко адаптировать, добавив разнообразные аксессуары, чтобы создать разные стили.",
                    "Черная куртка из овчины с текстурными вставками придает этому комплекту уникальность и стиль, а широкие черные джинсы создают комфортный и расслабленный вид. Этот наряд идеально подойдет для городских прогулок или для посещения зимних мероприятий, где ваш мальчик будет выглядеть как истинный модник, готовый к любым приключениям.",//
                    "Бежевый авиатор и кофта полузамок создают теплый и стильный комплект для зимних прогулок. Это отличный выбор для уютных встреч и городских поездок. Однако, будьте осторожны со светлыми тонами в условиях сильных осадков, чтобы не испачкаться.",
                    "Темно-зеленые штаны карго обеспечивают функциональность и стиль, а куртка с мехом добавляет тепла и уюта. Клетчатая рубашка придаёт образу нотку классики и оригинальности. Этот комплект отлично подойдет для зимних походов на природу или для активных игр с друзьями, где ваш мальчик сможет проявить свою индивидуальность и стиль.",//
                    "Утепленная черная глянцевая куртка, черные ботинки на каблуке, черные широкие клеш брюки и серый свитер с горлом — это уверенный и современный выбор для холодной погоды. Этот наряд подойдет для прогулки по городу или встречи с друзьями в кафе. Черные оттенки и текстуры делают данный комплект менее подверженным загрязнениям в зимний период.",
                    "Этот комплект включает в себя белую водолазку, черные брюки и стильные ботинки, дополненные бежевым длинным пуховиком — идеальный образ для зимних приключений. Облачившись в такой наряд, ваш ребенок будет готов к прогулкам в парке, посещению музея или поездке с классом с друзьями. Уютный пуховик обеспечит тепло и защиту от холода, позволяя наслаждаться зимними радостями без забот!",//
                )
            }
//            temp >10 -> {
//                imageId = arrayOf(R.drawable.light_jacket, R.drawable.sweater)
//                heading = arrayOf("Легкая куртка", "Свитер")
//                news = arrayOf("Наденьте легкую куртку или свитер.", "Наденьте свитер.")
//            }
//            temp > 25 -> {
//                imageId = arrayOf(R.drawable.tshirt, R.drawable.shorts)
//                heading = arrayOf("Футболка", "Шорты")
//                news = arrayOf("Наденьте футболку и шорты.", "Наденьте шорты.")
//            }
        }
        adapter.updateData(imageId, heading, news)
    }
}