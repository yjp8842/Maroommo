import React from 'react';
import { useDrag } from 'react-dnd';
import styles from './styles/Draggable.module.css';
import './styles/DeaggableHover.css';

function Draggable({ children, type, item, text, style, hideWhenDrag, state }) {
  const [{ isDragging }, drag] = useDrag(
    () => ({
      type,
      item,
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
    <div >
      <span className={`jb-title ${styles.draggable} ${isDragging && styles.dragging}`}
        style={style}
        ref={drag}
      >
        <span>{text.length > 3 ? text.slice(0,2) + '..': text}</span><br/>
        {children}
      </span>
      <div className="jb-text">{text}</div>
    </div>
  );
}

export default Draggable;

