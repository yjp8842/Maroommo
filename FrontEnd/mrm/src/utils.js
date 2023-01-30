export const formatDate = (dateString, separator = "-") => {
    const date = new Date(dateString);
    if (isNaN(date)) return;
  
    const yyyy = date.getFullYear();
    const mm = String(date.getMonth() + 1).padStart(2, "0");
    const dd = String(date.getDate()).padStart(2, "0");
  
    return `${yyyy}${separator}${mm}${separator}${dd}`;
  };