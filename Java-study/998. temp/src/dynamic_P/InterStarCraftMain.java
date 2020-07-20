package dynamic_P;

interface RResource{
	boolean get(String name,int limit);
}

interface BBuilding{
	void build(String name);
	void repair(String name);
}

interface UUnit{
	void repair(String name);
	void riding(String name);
}
interface Enemy{
	void attack(String name);
}

interface SSCVWork extends RResource,BBuilding,UUnit, Enemy {
	
}

class SCVData{
	String name;
	
	StarIndex [] resource = {
			new StarIndex("미네랄"),new StarIndex("가스")  
	};
	
	boolean resourceChk(String name) {
		boolean res = true;
		
		for(StarIndex bb : build) {
			if(bb.name.equals(name)) {
				for (int i = 0; i < resource.length; i++) {
					if(resource[i].cnt[0]<bb.money[i]) {
						res = false;
						break;
					}
				}
				if(res) {
					for (int i = 0; i < resource.length; i++) {
						resource[i].cnt[0]-=bb.money[i];
					}
				}
				
				
				
				break;
			}
		}
		
		return res;
	}
	
	StarIndex [] build = {
			new StarIndex("커맨드센터",400,0),
			new StarIndex("팩토리",200,100),
			new StarIndex("스타포트",150,100)
	};
	StarIndex [] unit = {
			new StarIndex("탱크"),new StarIndex("드랍쉽")  
	};
	
	StarIndex [] enemy = {
			new StarIndex("저글링")  
	};
	
	StarIndex [][] arr = {
			resource, build, unit, enemy
	};
	
	void addCnt(int kind, String name,int type, int cnt) {
		StarIndex [] buf = arr[kind];
		
		for (StarIndex index : buf) {
			if(index.name.equals(name)) {
				index.cnt[type] += cnt;
				
				return;
			}
		}
	}

	public SCVData(String name) {
		super();
		this.name = name;
	}
	
	void ppp() {
		System.out.println(name+">>>");
		String [] index = {"자원","건물","유닛","적"};
		for (int i = 0; i < index.length; i++) {
			System.out.println(" ==> ["+index[i]+"]");
			for (StarIndex si : arr[i]) {
				System.out.println(si.ppp());
			}
		}
	}
	
}

class StarIndex{
	String name;
	int [] cnt = new int[2];
	int [] money;
	public StarIndex(String name, int... money) {
		super();
		this.name = name;
		this.money = money;
	}
	public StarIndex(String name) {
		super();
		this.name = name;
	}
	
	String ppp() {
		return "["+name+":"+cnt[0]+","+cnt[1]+"]";
	}
}

class SSCV implements SSCVWork{

	SCVData myData, totData;
	
	
	
	public SSCV(SCVData totData, String name) {
		super();
		this.totData = totData;
		myData = new SCVData(name);
	}

	@Override
	public boolean get(String name, int limit) {
		int cnt = 8;
		totData.addCnt(0, name,0, cnt);
		myData.addCnt(0, name,0, cnt);
		System.out.println(totData.name+" "+myData.name+" "+name+" "+cnt+" 캐요");
		for (StarIndex index : totData.resource) {
			System.out.print(index.ppp()+",");
		}
		System.out.println();
		return indexCnt(totData.resource, name) >= limit;
	}
	
	int indexCnt(	StarIndex [] arr, String name) {
		for (StarIndex starIndex : arr) {
			if(starIndex.name.equals(name))
				return starIndex.cnt[0];
		}
		return 0;
	}

	@Override
	public void build(String name) {
		
		if(totData.resourceChk(name)) {
		
			totData.addCnt(1, name,0, 1);
			myData.addCnt(1, name,0, 1);
			System.out.println(totData.name+" "+myData.name+" "+name+" 지어요");
		}else {
			System.out.println(totData.name+" "+myData.name+" "+name+" 자원부족");
		}
	}

	@Override
	public void repair(String name) {
		totData.addCnt(1, name,1, 1);
		myData.addCnt(1, name,1, 1);
		totData.addCnt(2, name,1, 1);
		myData.addCnt(2, name,1, 1);
		System.out.println(totData.name+" "+myData.name+" "+name+" 고쳐요");
		
	}

	@Override
	public void riding(String name) {
		System.out.println(totData.name+" "+myData.name+" "+name+" 타요");
		
	}

	@Override
	public void attack(String name) {
		totData.addCnt(3, name,0, 1);
		myData.addCnt(3, name,0, 1);
		System.out.println(totData.name+" "+myData.name+" "+name+" 싸워요");
		
	}
	
}


public class InterStarCraftMain {
	
	static BBuilding bb;
	static RResource rr;
	static UUnit uu;
	static Enemy ee;
	
	static void getRe(String name,int limit, SSCV ... scvs) {
		while(true) {
			for (SSCV sscv : scvs) {
				rr = sscv;
				if(rr.get(name,limit))
					return;
			}
		}
	}
	

	public static void main(String[] args) {
		SCVData tot = new SCVData("3시");
		
		SSCV [] scv = {
				new SSCV(tot,"1호기" ),
				new SSCV(tot,"2호기" ),
				new SSCV(tot,"3호기" ),
				new SSCV(tot,"4호기" )
		};

		
		
		//rr = scvs[0];
		getRe("미네랄",500,scv[0],scv[1],scv[3]);
		bb = scv[2];
		bb.build("커맨드센터");
		bb.repair("커맨드센터");
		uu = scv[1];
		uu.riding("드랍쉽");
		uu.repair("드랍쉽");
		ee = scv[0];
		ee.attack("저글링");
		
		System.out.println();
		for (SSCV sscv : scv) {
			sscv.myData.ppp();
		}
		tot.ppp();
	}

}
