
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

비밀번호 개수 제한없도록 구현 완료!!

++아이콘 클릭시 숫자로 보이고 평상시엔 별 문자(*)로 보이도록 구현

## 2021.08.06 UI 버전업, Raspi로 데이터 푸시(TO MCU)

APP -> MCU로 데이터 푸시하기 위해 APP -> Raspi로 데이터 푸시 구현 완료(잠금방식설정)

알림바 클릭시 촬영된 위험인물 액티비티에 출력

잠금화면 최초 실행시/ 그 이후 실행시 구분

## 2021.08.07 MCU로 데이터 푸시 기능(2)

2차 잠금에 대한 보안, 해제 데이터 push 구현

MCU의 비밀번호 변경을 위한 데이터 push 진행중

## 2021.08.09 MCU로 데이터 푸시 기능(3)

MCU 비밀번호 변경 데이터 push 완료

보안 방식 어플로 잠금해제 데이터 처리 완료

강제로 금고를 열 때 알람 기능 구현 완료

## 2021.08.21 갤러리에서 사용자 및 위험인물 설정 기능 추가

기존 인물 설정시 Service로 Intent를 통하여 데이터를 서버로 보냄

--> 사진 특성상 데이터 크기가 크기 때문에 Intent로 보내기엔 에러가 발생!

따라서 Service의 클라이언트 객체를 public으로 지정하여 해당 액티비티에서 참조하여 해결

사진 선택(갤러리 or 사진 촬영)이 되었을 때만  금고의 얼굴인식 대상을 설정할 수 있도록 구현

## 2021.09.01 위험인물 접근 사진 load 기능 추가

기존 어플리케이션에 알림을 통해 위험인물이 접근하면 액티비티를 통해 사진을 확인할 수 있음

->but, 과거에 접근한 사진들을 load하는 기능이 없었지만 이번 version을 통해 기능 구현

UI가 날짜형태로 표현되어 있어 확인하는데 다소 보기 어려울수 있음, 따라서 UI  개선이 필요함.

## 2021.09.24 User_seting 클래스 수정

기존 유저와 위험인물에 대한 설정에 있어서 Json으로 키,값을 나누어 서버에 Parsing하려는 시도에 있어 오류

--> toString하는 과정에서 해결하기 난해하여, topic을 두개로 나누어 진행

--> 금고에 설정 된 인물을 추가하는 것만이 아닌 Load & Delete 기능도 추가할 예정

## 2021. 09.30 최신화된 통신 규약 구현

라즈베리파이의 경량화를 위한 코드수정, 그리고 통신규약 최신화

--> 변화에 따른 알림 문구 설정 및 intent 코드 작성

## 2021. 10.14 UI Update, Beacon 구현

전체적인 UI 재구성

Beacon 스캔 및 선택 그리고 스캔 여부에 따른 서버로의 데이터 전송
