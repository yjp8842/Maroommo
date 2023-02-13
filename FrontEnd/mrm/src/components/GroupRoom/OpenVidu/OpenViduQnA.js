import './OpenViduQnA.css';

const OpenViduQnA = () => {
  return (
    <div className="qna-box">
      <div className='selectBox'>
        <select name="cate1">
          {/* 해당 그룹의 카테고리로 바꾸기 */}
          <option value="리액트">리액트</option>
          <option value="CS">CS</option>
          <option value="알고리즘">알고리즘</option>
        </select>

        <select name="cate2">
          {/* 게시판 & Q&A로만 이루어짐 */}
          <option>게시판</option>
          <option>Q&A</option>
        </select>

        {/* OK버튼 눌렀을 때 해당 데이터 불러오기 */}
        <button>OK</button>
      </div>

    </div>
  )
}

export default OpenViduQnA;