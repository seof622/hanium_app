
# SMARTLOCK_PROJECT
## Application
## 2021.04.28 개발환경 구축

Android Studio

## 2021.06.25 잠금화면 구성

어플 실행시 비밀번호 설정

비밀번호 입력

- 입력한 비밀번호와  설정한 비밀번호가 일치

->비밀번호 비활성화, 비밀번호 변경 가능

- 입력한 비밀번호와 설정한 비밀번호가 불일치

-> 다시입력

## 2021.06.28 메인 화면 구성

어플 실행시 비밀번호 설정

비밀번호 입력 후 설정한 비밀번호와 일치하면 메인화면 이동

메인 화면에서 설정 가능

## 2021.07.05 잠금화면 및 메인 화면 UI 업데이트

직관적인 표현을 위해 메인화면을 금고 이미지로 디자인

잠금화면
- 비밀번호를 입력하지 않았을 때

->모든 금고가 닫혀있음

- 비밀번호를 입력하였을때

->금고가 하나씩 열리는 애니메이션

- 비밀번호를 지울때

->지운 비밀번호 자릿수에 금고가 하나씩 닫히는 애니메이션

## 2021.07.08 MQTT 통신 완료

AWS로 구축한 가상서버에서 mosquitto 서버 구축

특정 Topic을 Subscribe하여 라즈베리파이로부터 인식결과를 텍스트, 이미지 형태로 수신받음

--> 메인 화면에 출력

mqtt 프로토콜을 이용하기 위해서는 support -v4가 gradle 의존성에 추가되어야함

-->필자는 v4:28 으로 추가하였으므로 기기의 API가 28로 설정되어야 함

-->버전에 따라서 SDK의 Version을 일치 시켜야함

## 2021.07.18 알림,메인 화면 메뉴바 구성

라즈베리파이에서 위험인물이 인식되었을 때 알림 기능 설정

(NotificationManager).. 수정

비밀번호 변경(어플,금고), 보안 방식 설정 등 여러가지 설정을 위한 메뉴바 구성

Drawlayout을 이용하여 메인 화면에서의 설정 버튼 Click Event를 통하여 메뉴바 Open

따로 메뉴바에 대한 layout을 만들어주고 메뉴 화면 xml파일에 include로 추가해줘야함


## 2021.07.25 어플에서 사용자 변경 및 키패드 랜덤 깜빡임
MQTT 프로토콜 이용 어플에서 찍은 사진에 대하여 얼굴인식 할 대상을 바꾸는 기능 구현
--> 갤러리와 연동하여 기존에 있는 사진에 대해서 서버로 전송하는 기능 추가 구현 예정
잠금화면에서 키패드를 눌렀을 때 랜덤으로 다른 숫자가 깜빡이는 기능 추가(보안 강화)
--> 비밀번호 개수에 제한없도록 기능 추가 구현 예정
