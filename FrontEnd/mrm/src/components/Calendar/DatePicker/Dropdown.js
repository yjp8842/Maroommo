import { BsChevronDown } from "react-icons/bs";
import { PERIOD } from "./constants";
import "./Dropdown.css";

const Dropdown = (props) => {
  return (
    <div className="dropdown">
      <button onClick={props.toggleDropdown}>
        {props.selectedPeriod}
        <BsChevronDown />
      </button>
      {props.isDropdownOpen && (
        <ul>
          {PERIOD.map((item) => (
            <li key={item.id}>
              <button value={item.name} onClick={props.onClickPeriod}>
                {item.name}
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default Dropdown;
