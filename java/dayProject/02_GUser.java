class 원장 
{
	public static void main(String[] args) 
	{
		비트캠프 비트 = new 비트캠프();
		비트.정보();

		학생 학 = new 학생();
		학.수강등록(비트.과목1);
		학생 학2 = new 학생("김태희","제주대","남자",29);
		학2.수강등록(비트.과목3);

		학부모 부모 = new 학부모();
		부모.학원에전화한다(학);
		부모.학원에전화한다(학2);
		부모.딸에게전화한다(학.이름);
		부모.딸에게전화한다(학2.이름);
	}
}
