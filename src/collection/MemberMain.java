package collection;

import java.util.List;
import java.util.Scanner;

public class MemberMain {

    public static void main(String[] args) {
        MemberRepository repo = new MemberRepository();
        // 샘플 데이터 만들디
        Member member1 = new Member("홍길동1", "a@naver.com", 10);
        Member member2 = new Member("홍길동2", "b@naver.com", 10);
        Member member3 = new Member("홍길동3", "c@naver.com", 10);

        // memberRepository에 테스트를 위한 샘플 데이터 save() 처리
        repo.save(member1.getName(), member1.getEmail(), member1.getAge());
        repo.save(member2.getName(), member2.getEmail(), member2.getAge());
        repo.save(member3.getName(), member3.getEmail(), member3.getAge());

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== 회원 관리 시스템 ===");
            System.out.println("전체 회원: " + repo.count() + "명");
            System.out.println("1. 회원 가입");
            System.out.println("2. ID 로 조회");
            System.out.println("3. 이름으로 조회");
            System.out.println("4. 회원 수정");
            System.out.println("5. 회원 삭제");
            System.out.println("6. 전체 목록");
            System.out.println("7. 나이 범위 탐색");
            System.out.println("8. 이메일 탐색");
            System.out.println("9. 종료");
            System.out.print("선택 : ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.print("이름  : ");
                String name = sc.nextLine();
                System.out.print("이메일 : ");
                String email = sc.nextLine();
                System.out.print("나이  : ");
                int age = Integer.parseInt(sc.nextLine());
                // 유효성 체크 -- 메모리나 DB 안에 들어가는 데이터들의 무결성을 위해
                // 앞 뒤 에서 들어오는 데이터가 유효한지 확인한다.
                repo.save(name.trim(), email.trim(), age);

            } else if (choice.equals("2")) {
                System.out.print("조회할 ID : ");
                int id = Integer.parseInt(sc.nextLine());
                Member member = repo.findById(id);
                if (member != null) {
                    System.out.println("조회 결과: " + member);
                } else {
                    System.out.println("해당 회원이 없습니다.");
                }

            } else if (choice.equals("3")) {
                System.out.print("조회할 이름 : ");
                String name = sc.nextLine();
                List<Member> result = repo.findByName(name);
                if (result.isEmpty()) {
                    System.out.println("해당 이름의 회원이 없습니다.");
                } else {
                    for (Member m : result) {
                        System.out.println(m);
                    }
                }

            } else if (choice.equals("4")) {
                System.out.print("수정할 ID : ");
                int id = Integer.parseInt(sc.nextLine());
                System.out.print("새 이름   : ");
                String newName = sc.nextLine();
                System.out.print("새 나이   : ");
                int newAge = Integer.parseInt(sc.nextLine());
                repo.update(id, newName, newAge);

            } else if (choice.equals("5")) {
                System.out.print("삭제할 ID : ");
                int id = Integer.parseInt(sc.nextLine());
                repo.delete(id);

            } else if (choice.equals("6")) {
                System.out.println("\n=== 전체 회원 목록 ===");
                List<Member> all = repo.findAll();
                if (all.isEmpty()) {
                    System.out.println("등록된 회원이 없습니다.");
                } else {
                    for (Member m : all) {
                        System.out.println(m);
                    }
                }

            } else if (choice.equals("7")) {
                System.out.print("검색할 최소 나이를 입력하세요 : ");
                int minAge = Integer.parseInt(sc.nextLine());
                System.out.print("검색할 최대 나이를 입력하세요 : ");
                int maxAge = Integer.parseInt(sc.nextLine());
                System.out.println(repo.findByAgeRange(minAge, maxAge));

            } else if (choice.equals("8")) {
                System.out.print("검색할 이메일을 입력하세요 : ");
                String targetEmail = sc.nextLine();
                System.out.println(repo.findByEmail(targetEmail));

            } else if (choice.equals("9")) {
                System.out.println("시스템을 종료합니다.");
                break;
            }
        }
    }
}