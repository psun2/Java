package thread_p;

import java.util.ArrayList;
import java.util.StringTokenizer;

class MP3 extends Thread {

	ArrayList lyrics;

	public MP3(String lyrics) {

		this.lyrics = new ArrayList();

		init(lyrics);

	}

	void init(String lyrics) {

		StringTokenizer stn = new StringTokenizer(" ");

		while (stn.hasMoreTokens()) {

			String buf = stn.nextToken();
			
			System.out.println(buf);
			
		}

	}

	@Override
	public void run() {

		while (!lyrics.isEmpty()) {

		}

	}

	void play() {

	}
}

public class ThreadMP3Exam {

	public static void main(String[] args) {

		String lyrics = "단단한 갑옷\r\n" + "우뚝 솟은 큰 뿔\r\n" + "나는 천하장사\r\n" + "장수풍뎅이\r\n" + "달콤한 나무즙 \r\n"
				+ "절대 양보 못 해! \r\n" + "\r\n" + "칭칭 챙챙  \r\n" + "뒤집혀라, 뒤집혀!  \r\n" + "칭칭 챙챙   \r\n" + "큰 뿔 공격!\r\n"
				+ " “으아악!”\r\n" + " \r\n" + "위풍당당 풍뎅이 \r\n" + "장수풍뎅이   \r\n" + "위풍당당 풍뎅이 \r\n" + "장수풍뎅이, 화이팅!    \r\n"
				+ "\r\n" + "매끈한 몸통\r\n" + "날카로운 큰 턱\r\n" + "나는 싸움 대장\r\n" + "사슴벌레 \r\n" + "달콤한 나무즙 \r\n"
				+ "절대 포기 못 해! \r\n" + "\r\n" + "칭칭 챙챙  \r\n" + "뒤집혀라, 뒤집혀!  \r\n" + "칭칭 챙챙   \r\n" + "큰 턱 공격!\r\n"
				+ "“으아악!”\r\n" + "기세등등 으랏차\r\n" + "사슴벌레 \r\n" + "기세등등 으랏차\r\n" + "사슴벌레, 화이팅!  \r\n" + "\r\n"
				+ "위풍당당 힘센 \r\n" + "장수풍뎅이 \r\n" + "기세등등 멋진 \r\n" + "사슴벌레 \r\n" + "으랏차차 차차! \r\n" + "누가 누가 이길까?\r\n"
				+ "\r\n" + "칭칭 챙챙   \r\n" + "뒤집혀라, 뒤집혀!\r\n" + "칭칭 챙챙  \r\n" + "뒤집혀라, 뒤집혀!\r\n" + "“과연 누굴까?”  \r\n"
				+ "“힝, 누가 우리 좀 뒤집어 줘!” ";

//		System.out.println(lyrics);
		
		MP3 mp3 = new MP3(lyrics);

	}

}
