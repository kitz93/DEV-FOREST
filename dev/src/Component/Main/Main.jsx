import styled from "styled-components";

const TestDiv = styled.div`
  width: 1000px;
  background-color: skyblue;
  margin: auto;
`;

const Main = () => {
  return (
    <>
      <br />
      <br />
      <TestDiv>
        <div
          id="carouselExampleSlidesOnly"
          class="carousel slide"
          data-bs-ride="carousel"
        >
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img
                src="http://localhost/uploads/mainImg.png"
                class="d-block w-100"
                alt="..."
              />
            </div>
            <div class="carousel-item">
              <img
                src="http://localhost/uploads/mainImg2.png"
                class="d-block w-100"
                alt="..."
              />
            </div>
            <div class="carousel-item">
              <img
                src="http://localhost/uploads/mainImg3.png"
                class="d-block w-100"
                alt="..."
              />
            </div>
          </div>
        </div>
      </TestDiv>

      <div>
        <span>모두의 퀴즈 퀴즈 우리 모두 한 걸음</span>
      </div>
    </>
  );
};

export default Main;
