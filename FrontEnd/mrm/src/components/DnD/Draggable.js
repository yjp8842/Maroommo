import React from 'react';
import { useDrag } from 'react-dnd';
import styles from './styles/Draggable.module.css';

function Draggable({ children, type, item, content, tag, style, hideWhenDrag, state }) {
  const [{ isDragging }, drag] = useDrag(
    () => ({
      type,
      item,
      tag,
      collect: (monitor) => ({
        isDragging: !!monitor.isDragging(),
      }),
    }),
    [state]
  );

  if (isDragging && hideWhenDrag) {
    return <div ref={drag}></div>;
  }

  return (
    <span
      className={`${styles.draggable} ${isDragging && styles.dragging}`}
      style={style}
      ref={drag}
    >
      <span>{content}</span><br/>
      <span>{tag}</span>
      {children}
    </span>
  );
}

export default Draggable;
