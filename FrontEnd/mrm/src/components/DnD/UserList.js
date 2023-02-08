import React, { useContext } from 'react';
import { UserDispatch } from './TodoCreate';

const User = React.memo(function User({ user }) {
  const dispatch = useContext(UserDispatch);

  return (
    <div>
        <span>[{user.id}]</span>
      <b
        style={{
          cursor: 'pointer',
          color: user.active ? 'green' : 'black'
        }}
        onClick={() => {
          dispatch({ type: 'TOGGLE_USER', id: user.id });
        }}
      >
        {user.Todo_text}
      </b>
      &nbsp;
      <span>#{user.Todo_tag}</span>
      <span>({user.Group_id})</span>
      <span>/{user.Year}</span>
      <span>/{user.Month}</span>
      <span>/{user.Day}/</span>
      <button
        onClick={() => {
          dispatch({ type: 'REMOVE_USER', id: user.id });
        }}
      >
        삭제
      </button>
    </div>
  );
});

function UserList({ users }) {
  return (
    <div>
      {users.map(user => (
        <User user={user} key={user.id} />
      ))}
    </div>
  );
}

export default React.memo(UserList); // 두번째 파라미터를 지웠습니다
