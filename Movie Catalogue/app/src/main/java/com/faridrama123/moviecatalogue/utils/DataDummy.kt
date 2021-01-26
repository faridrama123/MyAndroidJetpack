package com.faridrama123.moviecatalogue.utils

import com.faridrama123.moviecatalogue.data.MOVIEEntity
import com.faridrama123.moviecatalogue.data.TvSHOWEntity
import java.util.*

object DataDummy {
    @JvmStatic
    fun generateDummyMovie(): ArrayList<MOVIEEntity> {
        val movie = ArrayList<MOVIEEntity>()
        movie.add(MOVIEEntity("1",
                "Wonder Woman",
                "12/25/2020",
                "Fantasy, Action, Adventure",
                "74",
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg")
        )
        movie.add(MOVIEEntity("2",
                "The Croods: A New Age",
                "12/23/2020 ",
                "Adventure, Fantasy, Family, Animation",
                "78",
                "nicholas cage is funny caveman groog",
                "https://image.tmdb.org/t/p/w220_and_h330_face/tK1zy5BsCt1J4OzoDicXmr0UTFH.jpg\n")
        )
        movie.add(MOVIEEntity("3",
                "Monsters of Man",
                "12/08/2020 ",
                "Science Fiction",
                "72",
                "A robotics company vying to win a lucrative military contract team up with a corrupt CIA agent to conduct an illegal live field test. They deploy four weaponized prototype robots into a suspected drug manufacturing camp in the Golden Triangle, assuming they'd be killing drug runners that no one would miss. Six doctors on a humanitarian mission witness the brutal slaughter and become prime targets.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/1f3qspv64L5FXrRy0MF8X92ieuw.jpg")
        )
        movie.add(MOVIEEntity("4",
                "Honest Thief ",
                "10/16/2020 ",
                "Action, Thriller, Crime, Drama",
                "69",
                "A bank robber tries to turn himself in because he's falling in love and wants to live an honest life...but when he realizes the Feds are more corrupt than him, he must fight back to clear his name.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/zeD4PabP6099gpE0STWJrJrCBCs.jpg")
        )
        movie.add(MOVIEEntity("5",
                "Soul",
                "12/25/2020",
                "Animation, Comedy, Drama, Music, Fantasy",
                "85",
                "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/zSuRX1VmT0PwuHVV7zl4n1mwdL0.jpg")
        )
        movie.add(MOVIEEntity("6",
                "Cosmoball",
                "08/27/2020",
                "Science Fiction, Adventure",
                "47",
                "Cosmoball is a mesmerizing intergalactic game of future played between humans and aliens at the giant extraterrestrial ship hovering in the sky over Earth. A young man with enormous power of an unknown nature joins the team of hot-headed superheroes in exchange for a cure for his mother’s deadly illness. The Four from Earth will fight not only to defend the honor of their home planet in the spectacular game, but to face the unprecedented threat to the Galaxy and embrace their own destiny.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/bSpmhdaslwYH2fn2mj7cRcrN5Vi.jpg")
        )
        movie.add(MOVIEEntity("7",
                "Tenet",
                "08/26/2020",
                "Action, Thriller, Science Fiction",
                "74",
                "Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/k68nPLbIST6NP96JmTxmZijEvCA.jpg")
        )
        movie.add(MOVIEEntity("8",
                "Greenland",
                "07/29/2020",
                "Action, Thrille",
                "71",
                "John Garrity, his estranged wife and their young son embark on a perilous journey to find sanctuary as a planet-killing comet hurtles toward Earth. Amid terrifying accounts of cities getting levelled, the Garrity's experience the best and worst in humanity. As the countdown to the global apocalypse approaches zero, their incredible trek culminates in a desperate and last-minute flight to a possible safe haven.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/bNo2mcvSwIvnx8K6y1euAc1TLVq.jpg")
        )
        movie.add(MOVIEEntity("9",
                "Jiu Jitsu",
                "11/20/2020",
                "Action, Fantasy, Science Fiction",
                "54",
                "Every six years, an ancient order of jiu-jitsu fighters joins forces to battle a vicious race of alien invaders. But when a celebrated war hero goes down in defeat, the fate of the planet and mankind hangs in the balance.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg")
        )
        movie.add(MOVIEEntity("10",
                "Wander ",
                "12/04/2020",
                "Thriller, Crime, Mystery",
                "55",
                "After getting hired to probe a suspicious death in the small town of Wander, a mentally unstable private investigator becomes convinced the case is linked to the same 'conspiracy cover up' that caused the death of his daughter.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/2AwPvNHphpZBJDqjZKVuMAbvS0v.jpg")
        )
        return movie
    }

    @JvmStatic
    fun generateDummyTv(): ArrayList<TvSHOWEntity> {
        val tvshow = ArrayList<TvSHOWEntity>()
        tvshow.add(TvSHOWEntity("1",
                "The Mandalorian",
                "Nov 12, 2019",
                "Sci-Fi & Fantasy, Action & Adventure",
                "85",
                "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg")
        )
        tvshow.add(TvSHOWEntity("2",
                "The Good Doctor",
                "Sep 25, 2017",
                "Drama",
                "84",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?",
                "https://image.tmdb.org/t/p/w220_and_h330_face/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg")
        )
        tvshow.add(TvSHOWEntity("3",
                "Grey's Anatomy",
                "Mar 27, 2005",
                "Drama",
                "78",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg")
        )
        tvshow.add(TvSHOWEntity("4",
                "Lucifer",
                "Jan 25, 2016",
                "Crime, Sci-Fi & Fantasy",
                "83",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg")
        )
        tvshow.add(TvSHOWEntity("5",
                "Selena: The Series",
                "Dec 04, 2020",
                "Drama",
                "75",
                "As Mexican-American Tejano singer Selena comes of age and realizes her dreams, she and her family make tough choices to hold on to love and music.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/mYsWyfiIMxx4HDm0Wck7oJ9ckez.jpg")
        )
        tvshow.add(TvSHOWEntity("6",
                "Riverdale",
                "Jan 26, 2017",
                "Drama, Mystery",
                "86",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/4X7o1ssOEvp4BFLim1AZmPNcYbU.jpg")
        )
        tvshow.add(TvSHOWEntity("7",
                "Game of Thrones",
                "Feb 15, 2019",
                "Sci-Fi & Fantasy, Drama, Action & Adventure, Mystery",
                "84",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "https://www.themoviedb.org/t/p/w220_and_h330_face/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg")
        )
        tvshow.add(TvSHOWEntity("8",
                "The Umbrella Academy",
                "Feb 15, 2019",
                "Action & Adventure, Sci-Fi & Fantasy, Drama",
                "87",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg")
        )
        tvshow.add(TvSHOWEntity("9",
                "30 Coins",
                "Nov 29, 2020",
                "Crime, Mystery, Drama",
                "64",
                "Father Vergara—an exorcist, boxer and ex-convict—lives in a remote village in Spain. Hoping to be lost and forgotten, Vergara’s demons catch up to him.",
                "https://image.tmdb.org/t/p/w220_and_h330_face/b2i9aaV6ncULl9jYXEoPi7VFJg8.jpg")
        )
        tvshow.add(TvSHOWEntity("10",
                "His Dark Materials",
                "Nov 03, 2019",
                "Drama, Sci-Fi & Fantasy",
                "81",
                "Lyra is an orphan who lives in a parallel universe in which science, theology and magic are entwined. Lyra's search for a kidnapped friend uncovers a sinister plot involving stolen children, and turns into a quest to understand a mysterious phenomenon called Dust. She is later joined on her journey by Will, a boy who possesses a knife that can cut windows between worlds. ",
                "https://image.tmdb.org/t/p/w220_and_h330_face/g6tIKGc3f1H5QMz1dcgCwADKpZ7.jpg")
        )
        return tvshow
    }
}