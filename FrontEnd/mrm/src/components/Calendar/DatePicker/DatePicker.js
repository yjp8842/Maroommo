import { forwardRef } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "./DatePicker.css";

const CustomDatePicker = (props) => {
  const CustomInput = forwardRef((props, ref) => (
    <button className="datepicker-input" onClick={props.onClick} ref={ref}>
      {props.selectedPeriod === "전체" ? "YYYY/MM/DD" : props.value}
    </button>
  ));

  return (
    <div>
      <DatePicker
        selected={props.selectedDate}
        onChange={props.setSelectedDate}
        dateFormat="yyyy/MM/dd"
        customInput={<CustomInput selectedPeriod={props.selectedPeriod} />}
        showPopperArrow={false}
      />
    </div>
  );
};

export default CustomDatePicker;
