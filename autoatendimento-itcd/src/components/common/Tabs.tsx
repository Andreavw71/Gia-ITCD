import React, { useState } from 'react';

interface Tab {
  id: string;
  label: string;
  content: React.ReactNode;
  disabled?: boolean;
}

interface TabsProps {
  tabs: Tab[];
  activeTab?: string;
  onTabChange?: (tabId: string) => void;
}

const Tabs: React.FC<TabsProps> = ({ tabs, activeTab: controlledActive, onTabChange }) => {
  const [internalActive, setInternalActive] = useState(tabs[0]?.id || '');
  const activeTab = controlledActive || internalActive;

  const handleTabClick = (tabId: string) => {
    if (onTabChange) {
      onTabChange(tabId);
    } else {
      setInternalActive(tabId);
    }
  };

  const activeContent = tabs.find((t) => t.id === activeTab)?.content;

  return (
    <div>
      <div className="sefaz-tabs">
        {tabs.map((tab) => (
          <button
            key={tab.id}
            type="button"
            className={`sefaz-tab ${activeTab === tab.id ? 'sefaz-tab--active' : ''}`}
            onClick={() => handleTabClick(tab.id)}
            disabled={tab.disabled}
          >
            {tab.label}
          </button>
        ))}
      </div>
      <div className="sefaz-tab-content">{activeContent}</div>
    </div>
  );
};

export default Tabs;
