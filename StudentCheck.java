package methodtest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class StudentCheck {
	Scanner sc = new Scanner(System.in);

	String[][] stuList;

	public StudentCheck() {
		stuList = new String[][] { { "김태호", "1234", "출결여부" } };

	}

	public static void List(StudentCheck stuCheck) {

		System.out.println();
		System.out.println("/////// 학생 리스트 ///////");
		System.out.println();
		System.out.println("=====================================");
		System.out.println("| 번호 | | 이름  | |비밀번호| | 출석여부 |");
		System.out.println("=====================================");

		if (stuCheck.stuList.length > 0) {
			for (int j = 0; j < stuCheck.stuList.length; j++) {
				System.out.println("| " + (j + 1) + "번" + " |" + " | " + stuCheck.stuList[j][0] + " |" + " | "
						+ stuCheck.stuList[j][1] + " | " + "| " + stuCheck.stuList[j][2] + " |");
				System.out.println("=====================================");
			}
		} else {
			System.out.println("『배열이 비어있습니다』");
		}

	}

	public static void check(StudentCheck stuCheck) {
		String ans;
		do {

			System.out.println();
			System.out.println("/////// 학생 등록 ///////");
			System.out.println();
			System.out.println("자신의 이름을 입력해주세요: ");
			String stuName = stuCheck.sc.next();
			System.out.println();
			System.out.println("비밀번호를 설정해주세요: ");
			String stuPass = stuCheck.sc.next();

			String[][] newArr = Arrays.copyOf(stuCheck.stuList, stuCheck.stuList.length + 1);
			newArr[stuCheck.stuList.length] = new String[] { stuName, stuPass, "출결여부" };
			stuCheck.stuList = newArr;

			System.out.println("///////< 등록되었습니다 >///////");
			System.out.println();
			System.out.println(Arrays.deepToString(newArr[newArr.length - 1]));
			System.out.println();
			System.out.println("『다른 학생도 추가하시겠습니까? (네/아니요)』");
			ans = stuCheck.sc.next();
		} while (ans.equals("네"));
	}

	public static void stuRemove(StudentCheck stuCheck) {
		int x = 1;

		while (x < 5) {
			System.out.println();
			System.out.println("/////// 학생 삭제 ///////");
			System.out.println("삭제할 학생의 이름을 입력해주세요: ");
			String stuN = stuCheck.sc.next();
			for (int i = 0; i < stuCheck.stuList.length; i++) {
				if (stuN.equals(stuCheck.stuList[i][0])) {
					System.out.println();
					x = 1;
					System.out.println("삭제할 학생의 비밀번호를 입력해주세요: ");
					String stuP = stuCheck.sc.next();
					if (stuP.equals(stuCheck.stuList[i][1])) {
						System.out.println();
						System.out.println("///////< 삭제되었습니다 >///////");
						System.out.println();
						System.out.println("『삭제된 학생: " + stuCheck.stuList[i][0] + "』");
						stuCheck.stuList = removeArr(stuCheck.stuList, i);
						break;

					} else {
						System.out.println("『비밀번호가 틀립니다." + "( 삭제시도 가능횟수: " + x + "/ 5 )』");
						x++;
					}

				}

			}
			System.out.println("『해당 학생은 저희 학교에 재학중이 아닙니다." + "( 삭제시도 가능횟수: " + x + "/ 5 )』");
			x++;
		}
		if (x >= 5) {
			System.out.println("『삭제시도 가능횟수를 전부 소진하였습니다.』");
		}

	}

	private static String[][] removeArr(String array[][], int index) {

		String[][] newArr = new String[array.length][2];
		System.arraycopy(array, 0, newArr, 0, index);
		System.arraycopy(array, index + 1, newArr, index, array.length - index - 1);

		return newArr;

	}

	public static void dayCheck(StudentCheck stuCheck) {

		int hour = LocalDateTime.now().getHour();
		int min = LocalDateTime.now().getMinute();
		int x = 0;
		while (x < 5) {

			System.out.println();
			System.out.println("/////// 출석 체크 ///////");
			System.out.println();
			System.out.println("<<<<< 현재시간: " + hour + "시" + min + "분 >>>>>");
			System.out.println();
			System.out.println("출석체크를 진행할 학생의 이름을 입력해 주세요: ");
			String Name = stuCheck.sc.next();
			for (int i = 0; i < stuCheck.stuList.length; i++) {
				if (Name.equals(stuCheck.stuList[i][0])) {
					System.out.println();
					System.out.println("비밀번호를 입력해주세요: ");
					String Pass = stuCheck.sc.next();
					System.out.println();
					if (Pass.equals(stuCheck.stuList[i][1])) {
						if (hour < 9 && hour > 6) {
							stuCheck.stuList[i][2] = "정상출석";
							System.out.println("<<<<< 현재시간: " + hour + "시" + min + "분 >>>>>");
							System.out.println("<<<<< 정상출석 되었습니다. >>>>>");
							System.out.println();

						} else if (hour < 18 && hour > 8) {
							stuCheck.stuList[i][2] = "지각";
							System.out.println("<<<<< 현재시간: " + hour + "시" + min + "분 >>>>>");
							System.out.println("<<<<< 지각입니다. >>>>>");
							System.out.println();

						} else {
							System.out.println("<<<<< 현재시간: " + hour + "시" + min + "분 >>>>>");
							System.out.println("<<<<< 등교시간이 아닙니다. >>>>>");
						}

						System.out.println("출결현황: " + stuCheck.stuList[i][0] + " / " + stuCheck.stuList[i][2]);
						return;
					} else {
						x++;
						System.out.println("『비밀번호가 틀립니다." + "( 출석시도 가능횟수: " + x + "/ 5 )』");
						break;
					}
				}
			}
			System.out.println("『해당 학생은 저희 학교에 재학중이 아닙니다." + "( 출석시도 가능횟수: " + x + "/ 5 )』");
			if (x >= 5) {
				System.out.println("『출석시도 가능횟수를 전부 소진하였습니다.』");
			}
		}

	}

	public static void main(String[] args) {

		StudentCheck stuCheck = new StudentCheck();
		System.out.println("『메뉴를 활성화시키겠습니까 (네/아니요)』");
		String ans = stuCheck.sc.next();

		while (ans.equals("네")) {
			System.out.println();
			System.out.println("(((((( MENU ))))))");
			System.out.println();
			System.out.println("==================");
			System.out.println("||  1번. 학생등록  ||");
			System.out.println("||  2번. 학생삭제  ||");
			System.out.println("||  3번. 학생목록  ||");
			System.out.println("||  4번. 출석체크  ||");
			System.out.println("==================");
			System.out.println();
			System.out.println("『1번부터 4번까지 원하는 메뉴를 선택해주세요: ex) 1, 2, 3...』 ");
			String num = stuCheck.sc.next();

			if (num.equals("1")) {
				check(stuCheck);
			} else if (num.equals("2")) {
				stuRemove(stuCheck);
			} else if (num.equals("3")) {
				List(stuCheck);
			} else if (num.equals("4")) {
				dayCheck(stuCheck);
			} else {
				System.out.println();
				System.out.println("『번호가 잘못되었습니다.』");
				break;
			}
			System.out.println("『메뉴를 다시 활성화시키겠습니까? (네/아니요)』");
			ans = stuCheck.sc.next();

		}
		System.out.println("『시스템을 종료했습니다』");

	}

}
